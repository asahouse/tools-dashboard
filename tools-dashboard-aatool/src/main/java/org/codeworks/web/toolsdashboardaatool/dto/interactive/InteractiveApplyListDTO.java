package org.codeworks.web.toolsdashboardaatool.dto.interactive;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class InteractiveApplyListDTO implements Serializable {

    @Builder.Default
    private Long fromCreaterTimes = 0L;
    @Builder.Default
    private Long fromNotCreaterTimes = 0L;
    @Builder.Default
    private Long totalApplyTimes = 0L;
    @Builder.Default
    private Long totalApplyUser = 0L;
    private Integer date;

}
