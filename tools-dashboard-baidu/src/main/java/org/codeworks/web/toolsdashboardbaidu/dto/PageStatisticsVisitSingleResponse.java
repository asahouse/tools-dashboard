package org.codeworks.web.toolsdashboardbaidu.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class PageStatisticsVisitSingleResponse implements Serializable {
    private Integer uv;
    private Integer pv;
    private String dateTitle;
}
