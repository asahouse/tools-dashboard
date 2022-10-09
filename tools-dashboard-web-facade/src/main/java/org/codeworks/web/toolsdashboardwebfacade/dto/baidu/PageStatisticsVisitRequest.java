package org.codeworks.web.toolsdashboardwebfacade.dto.baidu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageStatisticsVisitRequest implements Serializable {

    private int start_date;
    private int end_date;
    private String metrics;
    private int site_id;
    private String searchWord;

    public String getMetrics() {
        return Optional.ofNullable(metrics).isPresent() ? metrics : "pv_count,visitor_count";
    }
}
