package com.baidu.statistics.login.om;

import lombok.Data;

@Data
public class AuthCode {
	/**
	 * 图片的格式, 如JPG
	 */
	private String imgtype;
	/**
	 * 图片的二进制内容,base64编码
	 */
	private String imgdata;
	/**
	 * 图片会话id
	 */
	private String imgssid;
}
