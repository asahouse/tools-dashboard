package com.baidu.statistics.login.om;

import com.baidu.statistics.login.core.RealDataLoginRequest;
import lombok.Data;

@Data
public class DoLoginRequest extends RealDataLoginRequest {
	/**
	 * 用户输入密码 （必填）
	 */
	private String password;
	/**
	 * 验证码字面 （选填）
	 */
	private String imageCode;
	/**
	 * 验证码会话id （选填）
	 */
	private String imageSsid;
	
	public DoLoginRequest() {
		this.functionName = "doLogin";
	}

	public DoLoginRequest(String password) {
		this();
		this.password = password;
	}
	
	public DoLoginRequest(String password, String imageCode, String imageSsid) {
		this();
		this.password = password;
		this.imageCode = imageCode;
		this.imageSsid = imageSsid;
	}
}
