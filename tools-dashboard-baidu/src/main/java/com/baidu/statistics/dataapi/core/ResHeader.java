package com.baidu.statistics.dataapi.core;

import com.baidu.statistics.dataapi.om.profile.Failure;
import lombok.Data;

import java.util.List;

@Data
public class ResHeader {
	/**
	 * 响应描述
	 */
	private String desc;
	/**
	 * 失败信息
	 */
	private List<Failure> failures;
	/**
	 * 操作数
	 */
	private Integer oprs;
	/**
	 * 请求耗时
	 */
	private Integer oprtime;
	/**
	 * 请求消耗的配额数
	 */
	private Integer quota;
	/**
	 * 剩余配额数
	 */
	private Integer rquota;
	/**
	 * 响应状态
	 */
	private Integer status;
}
