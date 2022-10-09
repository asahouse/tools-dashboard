package org.codeworks.web.toolsdashboarddiscovery;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ToolsDashboardDiscoveryApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ToolsDashboardDiscoveryApplication.class).web(true).run(args);
	}
}
