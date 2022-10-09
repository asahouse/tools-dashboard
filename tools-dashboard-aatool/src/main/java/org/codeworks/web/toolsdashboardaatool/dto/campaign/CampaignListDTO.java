package org.codeworks.web.toolsdashboardaatool.dto.campaign;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CampaignListDTO implements Serializable{

    @Builder.Default
    private Long countPublishCampaign = 0L;
    @Builder.Default
    private Long countAAWorkShop = 0L;
//    @Builder.Default
//    private Long countAAWorkShopActive = 0L;
//    @Builder.Default
//    private Long countActiveCampaign = 0L;
    @Builder.Default
    private Long countApplyCampaign = 0L;

    private Integer date;
}
