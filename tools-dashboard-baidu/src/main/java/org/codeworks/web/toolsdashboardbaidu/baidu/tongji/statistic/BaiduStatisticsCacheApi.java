package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic;

import com.baidu.statistics.dataapi.core.ResHeader;
import com.baidu.statistics.dataapi.core.TongjiResponse;
import com.baidu.statistics.dataapi.om.profile.GetDataCollectionRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataResponse;
import com.baidu.statistics.dataapi.om.profile.GetSiteListResponse;
import com.baidu.statistics.dataapi.svc.Report;
import com.baidu.statistics.exception.StaticsException;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.ErrorCodes;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic.exception.StatisticsBaiduException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by benjaminkc on 16/12/11.
 *
 * 百度统计API的连接实现类
 * 改用Redssion缓存,同时抽离report登录信息
 */
@Slf4j
@Component
public class BaiduStatisticsCacheApi {

    @Autowired
    private BaiduLogService baiduLogService;

    @Cacheable("baidu-statistics-sites")
    public GetSiteListResponse requestSiteList(){

        Report report = baiduLogService.getCacheReport();

        try{
            TongjiResponse<GetSiteListResponse> sitesInfo = report.getSiteList();
            Optional.ofNullable(sitesInfo.getHeader()).ifPresent(resHeader -> {
                if (!resHeader.getFailures().isEmpty()) {
                    throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error,
                            sitesInfo.getHeader().getFailures().stream().map(failure -> failure.toString()).toArray());
                }
            });

            return sitesInfo.getBody();
        }catch(StaticsException se) {
            log.error("BaiduStatisticsCacheApi :se: requestSiteList -> "+ se.getMessage());
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"requestSiteList"});
        }
    }

    @Cacheable("baidu-statistics-datas")
    @SneakyThrows({StaticsException.class, StatisticsBaiduException.class})
    public GetDataResponse requestData(GetDataRequest request){
        if (!Optional.ofNullable(request).isPresent())
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error,
                    new Object[]{"request is NULL!"});

        Report report = baiduLogService.getCacheReport();

        TongjiResponse<GetDataResponse> sitesInfo = report.getData(request);

        Optional.ofNullable(sitesInfo.getHeader()).ifPresent(resHeader -> {
            if (!resHeader.getFailures().isEmpty()) {
                throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error,
                        sitesInfo.getHeader().getFailures().stream().map(failure -> failure.toString()).toArray());
            }
        });

        GetDataResponse body = sitesInfo.getBody();

        Optional.ofNullable(body).orElseThrow(() -> new StatisticsBaiduException(
                ErrorCodes.baidu_statistics_request_error, new Object[]{"requestData"}));

        try {
            Optional.ofNullable(body.getResult()).ifPresent(result ->
                    result.put("metrics", request.getMetrics()));
        }catch (StatisticsBaiduException sbe){
            log.error("BaiduStatisticsCacheApi :sbe: body.result -> "+ sbe.getMessage());
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error,
                    new Object[]{"body error info", sbe.toString()});
        }

        return body;
    }

    @Cacheable("baidu-statistics-datas-collect")
    public List<GetDataResponse> requestCollectionData(GetDataCollectionRequest rqCol){

        List<GetDataResponse> resResult = new ArrayList<>();

        Optional.ofNullable(rqCol.getRequests()).ifPresent(rqs ->
            rqs.parallelStream().forEach(rq -> Optional.ofNullable(this.requestData(rq))
                    .ifPresent(rs -> resResult.add(rs))));

        log.debug("requestCollectionData Coollection successfully!");
        return resResult;
    }
}
