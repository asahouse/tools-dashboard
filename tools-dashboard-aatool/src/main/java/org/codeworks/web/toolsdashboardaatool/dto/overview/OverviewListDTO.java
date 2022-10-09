package org.codeworks.web.toolsdashboardaatool.dto.overview;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class OverviewListDTO implements Serializable {

    @Builder.Default
    private Long createCount = 0L;
    @Builder.Default
    private Long inviteCount = 0L;
    @Builder.Default
    private Long joinCount = 0L;
    @Builder.Default
    private Long userCount = 0L;
    private Integer date;

}
