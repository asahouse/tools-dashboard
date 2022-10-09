package com.baidu.statistics.dataapi.core;

import lombok.Data;

import java.io.Serializable;

@Data
public class TongjiResponse<T extends ApiResponse> implements Serializable{
	/**
	 * 响应头
	 */
	private ResHeader header;
	/**
	 * api 响应数据
	 */
	private T body;
}
