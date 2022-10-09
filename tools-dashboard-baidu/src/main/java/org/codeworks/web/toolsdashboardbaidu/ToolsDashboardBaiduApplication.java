package org.codeworks.web.toolsdashboardbaidu;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
public class ToolsDashboardBaiduApplication {

	public static void main(String[] args) {
		log.debug("ForkJoinPool Size : "+System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
		SpringApplication.run(ToolsDashboardBaiduApplication.class, args);
	}
}
