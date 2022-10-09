package com.baidu.statistics.dataapi.om.profile;

import lombok.Data;

@Data
public class Failure {
	/**
	 * error code
	 */
	private int code;
	/**
	 * 错误信息
	 */
	private String message;
	/**
	 * 错误参数提示
	 */
	private String position;
	private String content;
}
