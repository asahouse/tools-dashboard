package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class APlusIndexDigestListSiteDTO implements Serializable{
    private Integer date;
    @Builder.Default
    private String name = "";
    @Builder.Default
    private Long value = 0L;
}
