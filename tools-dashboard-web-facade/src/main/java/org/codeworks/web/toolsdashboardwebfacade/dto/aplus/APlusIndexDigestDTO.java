package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class APlusIndexDigestDTO implements Serializable{

    @Builder.Default
    private Long totalVisitor = 0L;
    @Builder.Default
    private Long totalCreator = 0L;
    @Builder.Default
    private Long totalContainPageUser = 0L;
    private APlusIndexDigestTopDTO top;
    @Builder.Default
    private Long totalUsedOverFiveTimes = 0L;
    private Double avgPage;

}
