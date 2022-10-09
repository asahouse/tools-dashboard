package org.codeworks.web.toolsdashboardaatool.dto.overview;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OverviewDigestDTO implements Serializable{

    @Builder.Default
    private Long createCount = 0L;
    @Builder.Default
    private Long inviteCount = 0L;
    @Builder.Default
    private Long joinCount = 0L;
    @Builder.Default
    private Long userCount = 0L;

}
