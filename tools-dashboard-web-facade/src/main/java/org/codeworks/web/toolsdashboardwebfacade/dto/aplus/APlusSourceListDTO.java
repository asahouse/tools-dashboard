package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class APlusSourceListDTO implements Serializable{
    private Integer date;
    @Builder.Default
    private Long shareFromABOPV = 0L;
    @Builder.Default
    private Long shareFromABOUV = 0L;
    @Builder.Default
    private Long shareNotFromABOPV = 0L;
    @Builder.Default
    private Long shareNotFromABOUV = 0L;
    @Builder.Default
    private Long aaWorkshopPV = 0L;
    @Builder.Default
    private Long aaWorkshopUV = 0L;
}
