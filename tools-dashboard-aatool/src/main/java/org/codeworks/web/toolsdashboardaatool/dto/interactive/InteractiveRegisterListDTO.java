package org.codeworks.web.toolsdashboardaatool.dto.interactive;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class InteractiveRegisterListDTO implements Serializable {
    @Builder.Default
    private Long totalRegisterTimes = 0L;
    @Builder.Default
    private Long totalRegisterUser = 0L;
    private Integer date;

}
