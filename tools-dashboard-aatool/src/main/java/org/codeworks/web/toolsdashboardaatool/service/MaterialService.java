package org.codeworks.web.toolsdashboardaatool.service;

import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardaatool.dto.FutureObject;
import org.codeworks.web.toolsdashboardaatool.dto.material.MaterialTemplateDateListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.material.MaterialTemplateListDTO;
import org.codeworks.web.toolsdashboardaatool.service.dsl.DSLTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MaterialService extends ABDashboardService {

    /**
     活动分享次数 - 来自活动创始人 = 室主的分享次数
     活动分享次数 - 来自非活动创建人 = 邀请人的分享次数 + 游客的分享次数
     活动分享总量 = 室主 + 游客 + 邀请人
     活动报名人数 - 来自活动创始人 = 通过室主产生报名的人数
     活动报名人数 - 来自非活动创始人 = 通过非室主产生报名的人数
     活动报名总人数 = 通过非室主产生报名的人数 + 通过室主产生报名的人数
     活动报名总次数 = 总报名次数
     */

    @Autowired
    DSLTemplateService dslTemplateService;

    @Cacheable("CacheMaterialServiceTemplateList")
    public List<MaterialTemplateDateListDTO> getTemplateList(
            List<Integer> templateIds, LocalDate start, LocalDate end){

        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);
        if (templateIds.isEmpty()) return new ArrayList<>();

        return durationDates.parallelStream().map(date -> {
            List<MaterialTemplateListDTO> items = templateIds.parallelStream().map(templateId ->
                this.buildDTO(templateId, date)).collect(Collectors.toList());

            return MaterialTemplateDateListDTO.builder().items(items).date(date).build();
        }).collect(Collectors.toList());
    }

    public MaterialTemplateListDTO buildDTO(Integer templateId, Integer date){
        LocalDate current = LocalDate.parse(String.valueOf(date), DateTimeFormatter.ofPattern("yyyyMMdd"));

        List<FutureObject<Long>> futureObjects = new ArrayList<>();


        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countCreator(current, current, true, templateId), date))).name("countCreator").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countInviter(current, current, true, templateId), date))).name("countInviter").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countJoiner(current, current, true, templateId), date))).name("countJoiner").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countPublishCampaign(current, current, true, templateId), date))).name("countPublishCampaign").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countAAWorkShop(current, current, true, templateId), date))).name("countAAWorkShop").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseSingleResultCountToLong(
                dslTemplateService.countAAWorkShopActive(current, current, templateId)))).name("countAAWorkShopActive").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseSingleResultCountToLong(
                dslTemplateService.countActiveCampaign(current, current, templateId)))).name("countActiveCampaign").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countApplyCampaign(current, current, true, templateId), date))).name("countApplyCampaign").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countABOShareTimes(current, current, true, templateId), date))).name("countShareFromCreator").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countApply(current, current, true, templateId), date))).name("countApplyTimes").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countRegister(current, current, true, templateId), date))).name("countRegisterUser").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countRegisterTimes(current, current, true, templateId), date))).name("countRegisterTimes").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countABOShareTimes(current, current, true, templateId), date))).name("countShareFromCreator").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countInviteShareTimes(current, current, true, templateId), date))).name("countInviteShareTimes").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countVisitorShareTimes(current, current, true, templateId), date))).name("countVisitorShareTimes").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countChannelApply(current, current, true, true, templateId), date))).name("countApplyFromCreator").build());

        futureObjects.add(FutureObject.<Long>builder().future(CompletableFuture.supplyAsync(() -> this.parseMultiResultCountToLong(
                dslTemplateService.countChannelApply(current, current, true, false, templateId), date))).name("countApplyNotFromCreator").build());


        Map<String, Long> result = this.sequence(futureObjects).join();

        Long countShareNotFromCreator = BigDecimal.valueOf(result.get("countInviteShareTimes"))
            .add(BigDecimal.valueOf(result.get("countVisitorShareTimes"))).longValue();

        Long countShare = BigDecimal.valueOf(result.get("countShareFromCreator"))
            .add(BigDecimal.valueOf(countShareNotFromCreator)).longValue();

        Long countApplyUser = BigDecimal.valueOf(result.get("countApplyFromCreator"))
                .add(BigDecimal.valueOf(result.get("countApplyNotFromCreator"))).longValue();


        return MaterialTemplateListDTO.builder()
                .templateId(templateId)
                .countCreator(result.get("countCreator"))
                .countInviter(result.get("countInviter"))
                .countJoiner(result.get("countJoiner"))
                .countPublishCampaign(result.get("countPublishCampaign"))
                .countAAWorkShop(result.get("countAAWorkShop"))
                .countAAWorkShopActive(result.get("countAAWorkShopActive"))
                .countActiveCampaign(result.get("countActiveCampaign"))
                .countApplyCampaign(result.get("countApplyCampaign"))
                .countShareFromCreator(result.get("countShareFromCreator"))
                .countShareNotFromCreator(countShareNotFromCreator)
                .countShare(countShare)
                .countApplyFromCreator(result.get("countShareFromCreator"))
                .countApplyNotFromCreator(result.get("countApplyNotFromCreator"))
                .countApplyTimes(result.get("countApplyTimes"))
                .countApplyUser(countApplyUser)
                .countRegisterUser(result.get("countRegisterUser"))
                .countRegisterTimes(result.get("countRegisterTimes"))
                .build();
    }

}
