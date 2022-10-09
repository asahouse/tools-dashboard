package com.baidu.statistics.dataapi.core;

import lombok.Data;

@Data
public class TongjiRequest<T extends ApiRequest> {
	/**
	 * 认证信息（必填）
	 */
	private AuthHeader header;
	/**
	 * api 请求（必填）
	 */
	private T body;

	private String api;
	
	public TongjiRequest() {
		super();
	}

	public TongjiRequest(AuthHeader header, T body, String api) {
		super();
		this.header = header;
		this.body = body;
		this.api = api;
	}
}
