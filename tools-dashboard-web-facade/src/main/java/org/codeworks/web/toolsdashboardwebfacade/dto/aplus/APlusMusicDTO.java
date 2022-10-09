package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class APlusMusicDTO implements Serializable {

    private Integer musicCode;

    @Builder.Default
    private Long useCount = 0L;
}
