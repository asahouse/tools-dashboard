package org.codeworks.web.toolsdashboardauthorization;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ToolsDashboardAuthorizationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToolsDashboardAuthorizationApplication.class, args);
	}
}
