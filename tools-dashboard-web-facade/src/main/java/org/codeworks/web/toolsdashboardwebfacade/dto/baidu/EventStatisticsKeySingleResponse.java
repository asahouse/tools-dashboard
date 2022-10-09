package org.codeworks.web.toolsdashboardwebfacade.dto.baidu;

import lombok.Builder;
import lombok.Data;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;

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
