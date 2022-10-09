package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic;

import com.alibaba.fastjson.JSON;
import com.baidu.statistics.common.StatisticConstant;
import com.baidu.statistics.dataapi.svc.Report;
import com.baidu.statistics.exception.StaticsException;
import com.baidu.statistics.login.core.LoginResponse;
import com.baidu.statistics.login.core.LoginReturnCode;
import com.baidu.statistics.login.om.DoLoginResponse;
import com.baidu.statistics.login.om.PreLoginRequest;
import com.baidu.statistics.login.om.PreLoginResponse;
import com.baidu.statistics.login.svc.Login;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.ErrorCodes;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic.exception.StatisticsBaiduException;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.utils.EhcacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Optional;

/**
 * Baidu 接口登录信息业务类
 * Report的查询. 维持使用ehcache,因为临时信息而已避免短时间内多次查询需要登录.1小时超时
 */
@Slf4j
@Service
public class BaiduLogService {

    @Autowired
    private EhcacheUtil ehcacheUtil;
    private static String CACHE_NAME_USERINFO = "baidu-statistics-userinfo";

    private static Login login = new Login();

    @PostConstruct
    private void init() {
        ehcacheUtil.createCache(CACHE_NAME_USERINFO, 100, 3600);//1hour
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

    private Report initReport(){
        Optional<DoLoginResponse> retData = this.doLogin();
        if (!retData.isPresent())
            throw new StatisticsBaiduException(ErrorCodes.baidu_statistics_request_error, new Object[]{"initReport"});

        ehcacheUtil.put(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO, retData.get());

        Report report = new Report(retData.get().getUcid(), retData.get().getSt());
        return report;
    }

    public Report getCacheReport(){
        Optional<DoLoginResponse> retData = ehcacheUtil.get(CACHE_NAME_USERINFO, CACHE_NAME_USERINFO);

        Report report;

        if (retData.isPresent()) {
            report = new Report(retData.get().getUcid(), retData.get().getSt());
        }else
            report = this.initReport();

        return report;
    }

}
