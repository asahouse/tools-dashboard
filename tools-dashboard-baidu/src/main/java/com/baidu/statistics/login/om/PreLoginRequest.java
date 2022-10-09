package com.baidu.statistics.login.om;

import com.baidu.statistics.login.core.RealDataLoginRequest;
import lombok.Data;

@Data
public class PreLoginRequest extends RealDataLoginRequest {
	public PreLoginRequest() {
		this.functionName = "preLogin";
	}
	
	public PreLoginRequest(String osVersion, String deviceType, String clientVersion) {
		this();
		this.osVersion = osVersion;
		this.deviceType = deviceType;
		this.clientVersion = clientVersion;
	}
	/**
	 * 客户端载体操作系统
	 */
	private String osVersion;
	/**
	 * 客户端载体类型
	 */
	private String deviceType;
	/**
	 * 客户端版本
	 */
	private String clientVersion;
}
