package org.codeworks.web.toolsdashboardwebfacade.dto.baidu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventStatisticsKeySingleRequest implements Serializable {
    private ReportPropertyFlag flag;
    private String name;
}
