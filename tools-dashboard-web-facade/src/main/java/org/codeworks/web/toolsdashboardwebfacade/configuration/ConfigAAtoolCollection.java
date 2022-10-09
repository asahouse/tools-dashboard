package org.codeworks.web.toolsdashboardwebfacade.configuration;

import lombok.Data;
import org.codeworks.web.toolsdashboardwebfacade.dto.ConfigUsingBean;
import org.codeworks.web.toolsdashboardwebfacade.dto.ConfigUsingStatus;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Data
public class ConfigAAtoolCollection {
    private List<String> items = new ArrayList<>();

    public List<ConfigUsingBean> getConfigObjects(){
        if (items.isEmpty()) return new ArrayList<>();

        return items.stream().map(s -> {
            String[] singleArray = s.split("&&");

            return ConfigUsingBean.builder()
                    .id(Integer.valueOf(singleArray[0]))
                    .name(singleArray[1])
                    .flag(ReportPropertyFlag.fromValue(singleArray[2]))
                    .status(ConfigUsingStatus.fromValue(singleArray[3]))
                    .build();
        }).collect(Collectors.toList());
    }
}
