package org.codeworks.web.toolsdashboardaatool.dto.interactive;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class InteractiveShareListDTO implements Serializable {

    @Builder.Default
    private Long fromCreaterTimes = 0L;
    @Builder.Default
    private Long fromNotCreaterTimes = 0L;
    private Integer date;

}
