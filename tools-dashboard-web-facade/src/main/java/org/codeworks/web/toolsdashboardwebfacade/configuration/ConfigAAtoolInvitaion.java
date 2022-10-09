package org.codeworks.web.toolsdashboardwebfacade.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Data
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "tools-dashboard.aatool.invitaion")
public class ConfigAAtoolInvitaion extends ConfigAAtoolCollection{
}
