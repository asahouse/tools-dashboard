package com.baidu.statistics.login.svc;

import com.alibaba.fastjson.JSON;
import com.baidu.statistics.common.StatisticConstant;
import com.baidu.statistics.login.core.LoginResponse;
import com.baidu.statistics.login.core.LoginReturnCode;
import com.baidu.statistics.login.om.*;
import com.baidu.statistics.login.svc.Login;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BaseLoginTest {
	
	private Login login = new Login();
	
	public boolean preLogin() throws Exception {
		PreLoginRequest request = new PreLoginRequest(StatisticConstant.OS_VERSION_WINDOWS, StatisticConstant.DEVICE_TYPE_PC, "1.0");
		LoginResponse<PreLoginResponse> response = login.preLogin(request);
		if (LoginReturnCode.OK != response.getReturnCode()) {
			log.error("[error] preLogin unsuccessfully with return code: " + response.getReturnCode());
			return false;
		}
		PreLoginResponse retData = response.getRealData();
		if (retData.getNeedAuthCode() == null) {
			log.error("[error] unexpected preLogin return data: " + JSON.toJSONString(response.getRealData()));
			return false;
		}
		if (retData.getNeedAuthCode() == true) {
			log.error("[error] preLogin return data format error: " + JSON.toJSONString(response.getRealData()));
			return false;
		}
		log.debug("[notice] preLogin successfully!");
		return true;
	}
	
	public DoLoginResponse doLogin() throws Exception {
		LoginResponse<DoLoginResponse> response = login.doLogin();
		if (LoginReturnCode.OK != response.getReturnCode()) {
			log.error("[error] doLogin unsuccessfully with return code: " + response.getReturnCode());
			return null;
		}
		DoLoginResponse retData = response.getRealData();
		if (retData.getRetcode() == null || retData.getUcid() == null
				|| retData.getSt() == null) {
			log.error("[error] doLogin return data format error: " + retData);
			return null;
		}
		if (retData.getRetcode() != DoLoginResponse.RETCODE_OK) {
			log.error("[error] doLogin unsuccessfully with retcode: " + retData);
			return null;
		}
		log.debug("[notice] doLogin successfully!");
		return retData;
	}
	
	public boolean doLogout(Integer ucid, String st) throws Exception {
		DoLogoutRequest request = new DoLogoutRequest(ucid, st);
		LoginResponse<DoLogoutResponse> response = login.doLogout(request);
		if (LoginReturnCode.OK != response.getReturnCode()) {
			log.error("[error] doLogout unsuccessfully with return code: " + response.getReturnCode());
			return false;
		}
		DoLogoutResponse retData = response.getRealData();
		if (retData.getRetcode() == null) {
			log.error("[error] doLogout return data format error: " + retData);
			return false;
		}
		log.debug("[notice] doLogout successfully!");
		return true;
	}
}
