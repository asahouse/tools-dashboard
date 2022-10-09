package com.baidu.statistics.dataapi.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.baidu.statistics.config.Config;
import com.baidu.statistics.config.ConfigFactory;
import com.baidu.statistics.exception.StaticsException;
import com.baidu.statistics.utils.NetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * 调用前提：成功登录获取用户id（ucid）及会话id（st）。
 * 请求地址：https://api.baidu.com/json/tongji/v1/ProductService/api
 * 仅支持post请求，设置编码为UTF-8
 * @author nianqin,BenjaminKC
 */
public class DataApiConnection {
	private String url;
	private Integer ucid;
	
	private Config config;
	private DataApiLog log;
	
	public DataApiConnection(Integer ucid) {
		super();
		init(ucid);
	}

	private void init(Integer ucid) {
		config = new ConfigFactory().getConfig();
		log = new DataApiLog();
		this.url = config.getString(Config.K_API_URL);
		this.ucid = ucid;
	}
	
	private String genPostData(TongjiRequest<?> data) {
		String result = JSON.toJSONString(data, (ValueFilter) (object, name, value) -> {
            if (value instanceof ParameterType) {
                return JSON.toJSONString(value);//对子参数存在指定类型的进行JSON字符串化
            }
            return value;
        }, new SerializerFeature[0]);
		return result;
	}

	public <T extends ApiResponse> TongjiResponse<T> post(TongjiRequest<?> data, final Class<T> clazz) throws StaticsException {
		log.setStartTime(System.currentTimeMillis());

		CloseableHttpClient client = HttpClients.createMinimal();
		HttpPost hp = new HttpPost(this.url + data.getApi());

		RequestConfig requestConfig = RequestConfig.custom()
				.setSocketTimeout(20000)
				.setConnectTimeout(20000).build();//设置请求和传输超时时间
		hp.setConfig(requestConfig);


		//hp.addHeader("UUID", config.getString(Config.K_UUID)); //必填，唯一标识符，与登录时一致
		hp.addHeader("UUID", NetUtil.getLocalMac());//使用本机MAC地址替代, UUID 必填，唯一标识符，与登录时一致
		hp.addHeader("USERID", String.valueOf(this.ucid)); //必填，成功登录后获取的用户id（ ucid）
		hp.addHeader("tracker", ""); //选填，请求定位符，可用于定位请求
		hp.addHeader("Content-Type", "data/json;charset=UTF-8");

		String postDate = genPostData(data);//组装POST JSON 数据
		log.setRequest(postDate);
		HttpEntity entityAtPost = new StringEntity(postDate, "UTF-8");
		hp.setEntity(entityAtPost);

		try {
			return client.execute(hp, response -> {
                if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    throw new StaticsException("响应报文状态码异常:" + response.getStatusLine().getStatusCode());
                }
                HttpEntity entityAtResponse = response.getEntity();
                String retStr = EntityUtils.toString(entityAtResponse, "UTF-8");

                log.setResponse(retStr);
                log.setEndTime(System.currentTimeMillis());
                log.print();

                TongjiResponse<T> rs = new TongjiResponse<>();

                JSONObject retJson = JSON.parseObject(retStr);

                ResHeader header = JSON.parseObject(retJson.getString("header"), ResHeader.class);
                rs.setHeader(header);

                if (Optional.ofNullable(retJson.getJSONObject("body")).isPresent()) {
                    JSONArray dataArray = retJson.getJSONObject("body").getJSONArray("data");//抽取返回JSON中数据部分
                    if (Optional.ofNullable(dataArray).isPresent() && !dataArray.isEmpty()) {
                        T body = JSON.parseObject(dataArray.getString(0), clazz);
                        rs.setBody(body);
                    }
                }
                return rs;
            });
		} catch (ClientProtocolException e) {
			throw new StaticsException(e.getMessage());
		} catch (IOException e) {
			throw new StaticsException(e.getMessage());
		}
	}


}
