package org.codeworks.web.toolsdashboardwebfacade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigUsingBean {
    private Integer id;
    private String name;
    private ReportPropertyFlag flag;
    private ConfigUsingStatus status;
}
