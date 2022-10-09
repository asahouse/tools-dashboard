package com.baidu.statistics.config;

public interface Config {
	
	//preLogin,doLogin URL
	String K_LOGIN_URL = "baidutongji.login_url";
	//DataApi URL
	String K_API_URL = "baidutongji.api_ul";
	//USERNAME
	String K_USERNAME = "baidutongji.username";
	//PASSWORD
	String K_PASSWORD = "baidutongji.password";
	//TOKEN
	String K_TOKEN = "baidutongji.token";
	//UUID, used to identify your device, for instance: MAC address 
	String K_UUID = "baidutongji.uuid";//已使用获取本机MAC替代
	//ACCOUNT_TYPE
	String K_ACCOUNT_TYPE = "baidutongji.account_type";

	
	void loadConfig();
	String getString(String key);
	Integer getInteger(String key);
}
