package org.codeworks.web.toolsdashboardbaidu.dto;

import lombok.Builder;
import lombok.Data;
import org.codeworks.web.toolsdashboardbaidu.enumtype.ReportPropertyFlag;

import java.io.Serializable;

@Data
@Builder
public class EventStatisticsKeySingleResponse implements Serializable {
    private Integer uv;
    private Integer pv;
    private String name;
    private ReportPropertyFlag flag;
    private int start_date;
    private int end_date;
}
