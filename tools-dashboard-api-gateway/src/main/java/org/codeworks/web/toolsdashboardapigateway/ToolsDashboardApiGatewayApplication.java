package org.codeworks.web.toolsdashboardapigateway;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@EnableFeignClients
@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
public class ToolsDashboardApiGatewayApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(ToolsDashboardApiGatewayApplication.class).web(true).run(args);
	}
}
