package org.codeworks.web.toolsdashboardauthorization.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "tools-dashboard.login")
public class PlatformUsers {
    private List<String> users = new ArrayList<>();
}
