package com.baidu.statistics.login.core;

import com.alibaba.fastjson.annotation.JSONField;
import com.baidu.statistics.config.Config;
import com.baidu.statistics.config.ConfigFactory;
import com.baidu.statistics.utils.NetUtil;
import lombok.Data;

@Data
public class LoginRequest<T extends RealDataLoginRequest> {
	/**
	 * 登入用户名（必填）
	 */
	@JSONField(ordinal = 1)
	private String username;
	/**
	 * Drapi权限码（必填）
	 */
	@JSONField(ordinal = 2)
	private String token;
	/**
	 * 登入阶段（必填）
	 */
	@JSONField(ordinal = 3)
	private String functionName;
	/**
	 * 唯一标识码（必填）
	 */
	@JSONField(ordinal = 4)
	private String uuid;
	/**
	 * 请求内容（必填）
	 */
	@JSONField(ordinal = 5)
	private T request;
	
	public LoginRequest() {
	}

	public void initPartDataUseConfig() {
		Config config = new ConfigFactory().getConfig();
		this.username = config.getString(Config.K_USERNAME);
		this.token = config.getString(Config.K_TOKEN);
		this.uuid = NetUtil.getLocalMac();
	}

	public void setRequest(T request) {
		this.request = request;
		if (request != null) {
			this.functionName = request.functionName;
		}
	}
}
