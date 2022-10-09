package org.codeworks.web.toolsdashboardwebfacade.dto.baidu;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventStatisticsKeyRequest implements Serializable {

    public List<EventStatisticsKeySingleRequest> events;
    public int start_date;
    public int end_date;
    public String metrics;
    public int site_id;

    public String getMetrics() {
        return Optional.ofNullable(metrics).isPresent() ? metrics : "event_count,uniq_event_count";
    }
}
