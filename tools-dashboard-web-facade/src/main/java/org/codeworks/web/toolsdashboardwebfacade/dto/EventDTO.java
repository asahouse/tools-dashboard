package org.codeworks.web.toolsdashboardwebfacade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO implements Serializable{

    private String name;
    @Builder.Default
    private Long uv = 0L;
    @Builder.Default
    private Long pv = 0L;
    private String flag;
    private Integer start_date;
    private Integer end_date;

}
