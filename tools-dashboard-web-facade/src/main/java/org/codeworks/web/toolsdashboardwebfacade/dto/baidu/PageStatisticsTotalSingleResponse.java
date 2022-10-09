package org.codeworks.web.toolsdashboardwebfacade.dto.baidu;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PageStatisticsTotalSingleResponse implements Serializable {
    private Integer uv;
    private Integer pv;
    private String dateTitle;
}
