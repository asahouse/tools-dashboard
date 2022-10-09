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
public class APlusTemplateDTO implements Serializable{

    @Builder.Default
    private Long creatorCount = 0L;
    @Builder.Default
    private Long galleryCount = 0L;
    @Builder.Default
    private Long likeCount = 0L;
    private Integer templateCode;
}
