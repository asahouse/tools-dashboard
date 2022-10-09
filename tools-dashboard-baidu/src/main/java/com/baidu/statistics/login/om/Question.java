package com.baidu.statistics.login.om;

import lombok.Data;

@Data
public class Question {
	/**
	 * 安全问题id
	 */
	private Integer qid;
	/**
	 * 安全问题字面
	 */
	private String content;
}
