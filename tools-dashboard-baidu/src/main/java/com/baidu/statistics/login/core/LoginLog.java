package com.baidu.statistics.login.core;

import com.baidu.statistics.log.StatisticLog;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class LoginLog implements StatisticLog {
	
	private String request;
	private String response;
	
	private long startTime;
	private long endTime;
	
	public void print() {
		String logStr = "\n" + "请求数据：" + request + "\n"
				+ "返回数据：" + response + "\n"
				+ "耗时：" + String.valueOf(this.endTime - this.startTime) + "ms";
		log.debug(logStr);
	}
}
