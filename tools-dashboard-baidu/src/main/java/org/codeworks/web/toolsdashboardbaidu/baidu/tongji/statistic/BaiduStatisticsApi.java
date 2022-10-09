package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic;

import com.alibaba.fastjson.JSON;
import com.baidu.statistics.common.StatisticConstant;
import com.baidu.statistics.dataapi.core.ResHeader;
import com.baidu.statistics.dataapi.core.TongjiResponse;
import com.baidu.statistics.dataapi.om.profile.GetDataCollectionRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataResponse;
import com.baidu.statistics.dataapi.om.profile.GetSiteListResponse;
import com.baidu.statistics.dataapi.svc.Report;
import com.baidu.statistics.exception.StaticsException;
import com.baidu.statistics.login.core.LoginResponse;
import com.baidu.statistics.login.core.LoginReturnCode;
import com.baidu.statistics.login.om.*;
import com.baidu.statistics.login.svc.Login;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.ErrorCodes;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic.exception.StatisticsBaiduException;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.utils.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by benjaminkc on 16/12/11.
 *
 * 百度统计API的连接实现类
 */
@Slf4j
@Component
public class BaiduStatisticsApi {

    private static String CACHE_NAME_SITE = "baidu-statistics-sites";
    private static String CACHE_NAME_DATAS = "baidu-statistics-datas";
    private static String CACHE_NAME_USERINFO = "baidu-statistics-userinfo";

    @Autowired
    private EhcacheUtil ehcacheUtil;

    private static Login login = new Login();

    @PostConstruct
    private void init() {
        ehcacheUtil.createCache(CACHE_NAME_SITE, 100, 864000);//1天
        ehcacheUtil.createCache(CACHE_NAME_DATAS, 1000, 2592000);//30天
        ehcacheUtil.createCache(CACHE_NAME_USERINFO, 100, 3600);//1hour
    }

    private Report initReport(){
        Optional<DoLoginResponse> retData = this.doLogin();
        if (!retData.isPresent())
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"initReport"});

        ehcacheUtil.put(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO, retData.get());

        Report report = new Report(retData.get().getUcid(), retData.get().getSt());
        return report;
    }

    private Report getCacheReport(){
        Optional<DoLoginResponse> retData = ehcacheUtil.get(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO);

        Report report;

        if (retData.isPresent()) {
            report = new Report(retData.get().getUcid(), retData.get().getSt());
        }else
            report = this.initReport();

        return report;
    }

    private boolean preLogin(){
        PreLoginRequest request = new PreLoginRequest(
                StatisticConstant.OS_VERSION_LINUX, StatisticConstant.DEVICE_TYPE_PC, "1.0");

        Optional<LoginResponse<PreLoginResponse>> resOpt;
        try {
            resOpt = Optional.ofNullable(login.preLogin(request));
        }catch (StaticsException se){
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"preLogin"});
        }

        if (!resOpt.isPresent()) throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"preLogin"});

        LoginResponse<PreLoginResponse> response = resOpt.get();


        if (LoginReturnCode.OK != response.getReturnCode()) {
            log.error("[error] preLogin unsuccessfully with return code: " + response.getReturnCode());
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_prelogin_error, new Object[]{response.getReturnCode()});
        }

        PreLoginResponse retData = response.getRealData();

        if (retData.getNeedAuthCode() == null) {
            log.error("[error] unexpected preLogin return data: " + JSON.toJSONString(response.getRealData()));
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_prelogin_auth, new Object[]{JSON.toJSONString(response.getRealData())});
        }
        if (retData.getNeedAuthCode() == true) {
            log.error("[error] preLogin return data format error: " + JSON.toJSONString(response.getRealData()));
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_prelogin_format, new Object[]{JSON.toJSONString(response.getRealData())});
        }

        log.debug("[notice] preLogin successfully!");
        return true;
    }

    private Optional<DoLoginResponse> doLogin(){

        if(!this.preLogin()) return Optional.empty();

        Optional<LoginResponse<DoLoginResponse>> resOpt;
        try{
            resOpt = Optional.ofNullable(login.doLogin());
        }catch (StaticsException se){
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"doLogin"});
        }

        if (!resOpt.isPresent()) throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error);

        LoginResponse<DoLoginResponse> response = resOpt.get();

        if (LoginReturnCode.OK != response.getReturnCode()) {
            log.error("[error] doLogin unsuccessfully with return code: " + response.getReturnCode());
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_dologin_error, new Object[]{response.getReturnCode()});
        }

        DoLoginResponse retData = response.getRealData();

        if (retData.getRetcode() == null || retData.getUcid() == null
                || retData.getSt() == null) {
            log.error("[error] doLogin return data format error: " + retData);
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_dologin_format, new Object[]{response.getReturnCode()});
        }
        if (retData.getRetcode() != DoLoginResponse.RETCODE_OK) {
            log.error("[error] doLogin unsuccessfully with retcode: " + retData);
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_dologin_return_error, new Object[]{response.getReturnCode()});
        }

        log.debug("[notice] doLogin successfully!");
        return Optional.ofNullable(retData);
    }

    public boolean doLogout(Integer ucid, String st){
        DoLogoutRequest request = new DoLogoutRequest(ucid, st);

        Optional<LoginResponse<DoLogoutResponse>> resOpt;

        try{
            resOpt = Optional.ofNullable(login.doLogout(request));
        }catch (StaticsException se){
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"doLogin"});
        }

        if (!resOpt.isPresent()) throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error);

        LoginResponse<DoLogoutResponse> response = resOpt.get();

        if (LoginReturnCode.OK != response.getReturnCode()) {
            log.error("[error] doLogout unsuccessfully with return code: " + response.getReturnCode());
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_dologout_error, new Object[]{response.getReturnCode()});
        }
        DoLogoutResponse retData = response.getRealData();
        if (retData.getRetcode() == null) {
            log.error("[error] doLogout return data format error: " + retData);
            throw new StatisticsBaiduException(
                    ErrorCodes.baidu_statistics_dologout_format, new Object[]{response.getReturnCode()});
        }
        log.debug("[notice] doLogout successfully!");
        return true;
    }



    public GetSiteListResponse requestSiteList(){

        String Key = "sites";

        Optional cache = ehcacheUtil.get(CACHE_NAME_SITE, Key);

        if (cache.isPresent()){
            GetSiteListResponse rq = (GetSiteListResponse)cache.get();
            return rq;
        }

        Report report = this.getCacheReport();

        try{
            TongjiResponse<GetSiteListResponse> sitesInfo = report.getSiteList();
            Optional<ResHeader> resHeader = Optional.ofNullable(sitesInfo.getHeader());
            if (!resHeader.get().getFailures().isEmpty()
                    && resHeader.get().getFailures().get(0).getCode()==8202) {
                this.initReport();
                return requestSiteList();
            }

            GetSiteListResponse body = sitesInfo.getBody();
            if (Optional.ofNullable(body).isPresent() &&
                    body.getList().size() > 0) {
                ehcacheUtil.put(CACHE_NAME_SITE, Key, body);//一天
            }
            return body;
        }catch(StaticsException se) {
            log.error("BaiduStatisticsApi :: requestSiteList -> "+ se.getMessage());
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"requestSiteList"});
        }
        finally {
            ehcacheUtil.remove(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO);
        }
    }

    public GetDataResponse requestData(GetDataRequest request){

        String reqJsonStr = JSON.toJSON(request).toString();
        String reqMD5 = DigestUtils.md5Hex(reqJsonStr);

        Optional cache = ehcacheUtil.get(CACHE_NAME_DATAS, reqMD5);
        if (cache.isPresent()){
            GetDataResponse rq = (GetDataResponse)cache.get();
            return rq;
        }

        Report report = this.getCacheReport();

        try{
            TongjiResponse<GetDataResponse> sitesInfo = report.getData(request);
            Optional<ResHeader> resHeader = Optional.ofNullable(sitesInfo.getHeader());
            if (!resHeader.get().getFailures().isEmpty()
                    && resHeader.get().getFailures().get(0).getCode()==8202) {
                this.initReport();
                return requestData(request);
            }

            GetDataResponse body = sitesInfo.getBody();
            if (Optional.ofNullable(body).isPresent()
                    && Optional.ofNullable(body.getResult()).isPresent()){
                ehcacheUtil.put(CACHE_NAME_DATAS, reqMD5, body);//30天
            }
            body.getResult().put("metrics", request.getMetrics());
            return body;
        }catch(Exception se){
            log.error("BaiduStatisticsApi :: requestData -> "+se.getMessage());
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"requestData"});
        }finally {
            ehcacheUtil.remove(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO);
        }
    }

    public List<GetDataResponse> requestCollectionData(GetDataCollectionRequest rqCol){

        List<GetDataResponse> resResutl = new ArrayList<>();
        Optional.ofNullable(rqCol.getRequests()).ifPresent(rqs -> {
            rqs.forEach(rq ->{
                GetDataResponse rs = this.requestData(rq);
                if (rs!=null) {
                    resResutl.add(rs);
                }
            });
        });
        log.debug("requestCollectionData Coollection successfully!");

        ehcacheUtil.remove(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO);
        return resResutl;
    }
}
