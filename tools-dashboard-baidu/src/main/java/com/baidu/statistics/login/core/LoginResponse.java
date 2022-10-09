package com.baidu.statistics.login.core;

import lombok.Data;

@Data
public class LoginResponse<T extends RealDataLoginResponse> {
	/**
	 * 表明成功与否及错误的具体代码
	 */
	private int returnCode;
	/**
	 * 加密版本
	 */
	private int encryptVersion;
	/**
	 * 保留字段1
	 */
	private int resevred;
	/**
	 * 保留字段2
	 */
	private int resevred2;
	/**
	 * 真实的返回数据
	 */
	private T realData;
}
