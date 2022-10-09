package org.codeworks.web.toolsdashboardbaidu.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codeworks.web.toolsdashboardbaidu.enumtype.ReportPropertyGran;

import java.io.Serializable;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageStatisticsTotalRequest implements Serializable {

    private int start_date;
    private int end_date;
    private String metrics;
    private int site_id;
    private ReportPropertyGran gran;

    public String getMetrics() {
        return Optional.ofNullable(metrics).isPresent() ? metrics : "pv_count,visitor_count";
    }

    public ReportPropertyGran getGran() {
        return Optional.ofNullable(gran).isPresent() ? gran : ReportPropertyGran.DAY;
    }
}
