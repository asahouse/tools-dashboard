package com.baidu.statistics.login.om;

import com.baidu.statistics.login.core.RealDataLoginResponse;
import lombok.Data;

@Data
public class DoLogoutResponse extends RealDataLoginResponse {
	public static final Integer RETCODE_OK = 0; //成功
	public static final Integer RETCODE_FAILED = 1; //失败
	/**
	 * 0：成功， 1：失败
	 */
	private Integer retcode;
	/**
	 * error 具体信息
	 */
	private String retmsg;
}
