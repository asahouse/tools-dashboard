package org.codeworks.web.toolsdashboardbaidu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codeworks.web.toolsdashboardbaidu.enumtype.ReportPropertyFlag;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventStatisticsKeySingleRequest implements Serializable {
    private ReportPropertyFlag flag;
    private String name;
}
