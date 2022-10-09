package org.codeworks.web.toolsdashboardwebfacade.dto.aplus;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class APlusGalleryListDTO implements Serializable{
    @Builder.Default
    private Long shareTotal = 0L;
    @Builder.Default
    private Long likeCount = 0L;
    @Builder.Default
    private Long galleryCount = 0L;
    private Integer date;
}
