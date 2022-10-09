package org.codeworks.web.tools.dashboard.myspace;

import co.paralleluniverse.springframework.boot.autoconfigure.web.FiberSpringBootApplication;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@EnableDiscoveryClient
@FiberSpringBootApplication
public class DashboardMyspaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardMyspaceApplication.class, args);
	}

	@Bean
	public ObjectMapper ObjectMapper(){
		ObjectMapper objectMapper=new ObjectMapper();
		return objectMapper;
	}

}
