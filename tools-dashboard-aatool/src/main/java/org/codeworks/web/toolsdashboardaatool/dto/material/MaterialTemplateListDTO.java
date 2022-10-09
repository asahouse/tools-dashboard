package org.codeworks.web.toolsdashboardaatool.dto.material;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class MaterialTemplateListDTO implements Serializable {

    private Integer templateId;
    @Builder.Default
    private Long countCreator = 0L;
    @Builder.Default
    private Long countInviter = 0L;
    @Builder.Default
    private Long countJoiner = 0L;
    @Builder.Default
    private Long countPublishCampaign = 0L;
    @Builder.Default
    private Long countAAWorkShop = 0L;
    @Builder.Default
    private Long countAAWorkShopActive = 0L;
    @Builder.Default
    private Long countActiveCampaign = 0L;
    @Builder.Default
    private Long countApplyCampaign = 0L;
    @Builder.Default
    private Long countShareFromCreator = 0L;
    @Builder.Default
    private Long countShareNotFromCreator = 0L;
    @Builder.Default
    private Long countShare = 0L;
    @Builder.Default
    private Long countApplyFromCreator = 0L;
    @Builder.Default
    private Long countApplyNotFromCreator = 0L;
    @Builder.Default
    private Long countApplyUser = 0L;
    @Builder.Default
    private Long countApplyTimes = 0L;
    @Builder.Default
    private Long countRegisterUser = 0L;
    @Builder.Default
    private Long countRegisterTimes = 0L;
}
