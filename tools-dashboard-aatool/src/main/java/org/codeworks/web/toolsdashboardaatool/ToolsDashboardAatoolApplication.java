package org.codeworks.web.toolsdashboardaatool;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@ServletComponentScan//对Servlet3的filter,监听器等进行默认扫描
public class ToolsDashboardAatoolApplication {

	public static void main(String[] args) {
		log.debug("ForkJoinPool Size : "+System.getProperty("java.util.concurrent.ForkJoinPool.common.parallelism"));
		SpringApplication.run(ToolsDashboardAatoolApplication.class, args);
	}

}
