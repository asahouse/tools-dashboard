package org.codeworks.web.toolsdashboardwebfacade.dto.baidu;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class EventStatisticsCollectResponse implements Serializable {
    private List<EventStatisticsKeySingleResponse> collect;
    private int date;
}
