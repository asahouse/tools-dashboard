package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class APlusSourceBaiduDTO implements Serializable{
    @Builder.Default
    private Long value = 0L;
    private String name;
    private Integer date;
}
