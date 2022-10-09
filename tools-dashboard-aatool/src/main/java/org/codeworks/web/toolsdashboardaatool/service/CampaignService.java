package org.codeworks.web.toolsdashboardaatool.service;

import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardaatool.dto.campaign.CampaignListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.material.MaterialTemplateDateListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.material.MaterialTemplateListDTO;
import org.codeworks.web.toolsdashboardaatool.service.dsl.DSLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CampaignService extends ABDashboardService {

    @Autowired
    DSLService dslService;

    public List<CampaignListDTO> getCampaignList(LocalDate start, LocalDate end){
        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);

        return durationDates.parallelStream().map(date -> this.buildDTO(date))
                .collect(Collectors.toList());
    }

    public CampaignListDTO buildDTO(Integer date){
        LocalDate current = LocalDate.parse(String.valueOf(date), DateTimeFormatter.ofPattern("yyyyMMdd"));

        Long countPublishCampaign = this.parseMultiResultCountToLong(
                dslService.countPublishCampaign(current, current, true), date);

        Long countAAWorkShop = this.parseMultiResultCountToLong(
                dslService.countAAWorkShop(current, current, true), date);

//        Long countAAWorkShopActive = this.parseSingleResultCountToLong(
//                dslService.countAAWorkShopActive(current, current));

//        Long countActiveCampaign = this.parseSingleResultCountToLong(
//                dslService.countActiveCampaign(current, current));

        Long countApplyCampaign = this.parseMultiResultCountToLong(
                dslService.countApplyCampaign(current, current, true), date);

        return CampaignListDTO.builder()
                .countAAWorkShop(countAAWorkShop)
//                .countAAWorkShopActive(countAAWorkShopActive)
//                .countActiveCampaign(countActiveCampaign)
                .countApplyCampaign(countApplyCampaign)
                .countPublishCampaign(countPublishCampaign)
                .date(date)
                .build();
    }
}
