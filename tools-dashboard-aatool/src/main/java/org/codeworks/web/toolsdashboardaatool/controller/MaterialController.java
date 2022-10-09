package org.codeworks.web.toolsdashboardaatool.controller;

import com.codahale.metrics.annotation.*;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardaatool.dto.material.MaterialTemplateDateListDTO;
import org.codeworks.web.toolsdashboardaatool.dto.material.MaterialTemplateListDTO;
import org.codeworks.web.toolsdashboardaatool.service.MaterialService;
import org.codeworks.web.toolsdashboardaatool.utils.ArrayPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("material")
@RestController
public class MaterialController {

    @Autowired
    MaterialService materialService;

    @Timed//时间类分析数值
    @ExceptionMetered//异常分析数值
    @GetMapping("template/graph")
    public Response templateGraph(@RequestParam List<Integer> templateIds,
                             @RequestParam String start,
                             @RequestParam String end,
                             @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<MaterialTemplateDateListDTO> result = materialService.getTemplateList(templateIds,
            LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
            .parallelStream().sorted(
                    Comparator.comparing(MaterialTemplateDateListDTO::getDate).reversed())
            .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

    @Timed//时间类分析数值
    @ExceptionMetered//异常分析数值
    @GetMapping("template/list")
    public Response templateList(@RequestParam List<Integer> templateIds,
                             @RequestParam String start,
                             @RequestParam String end,
                             @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                             @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<MaterialTemplateListDTO> result = materialService.getTemplateList(templateIds,
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parallelStream().flatMap(r -> r.getItems().parallelStream())
                .collect(Collectors.groupingBy(MaterialTemplateListDTO::getTemplateId))
                .entrySet().parallelStream().map(single ->
                    MaterialTemplateListDTO.builder()
                            .templateId(single.getKey())
                            .countCreator(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountCreator).sum())
                            .countInviter(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountInviter).sum())
                            .countJoiner(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountJoiner).sum())
                            .countPublishCampaign(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountPublishCampaign).sum())
                            .countAAWorkShop(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountAAWorkShop).sum())
                            .countAAWorkShopActive(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountAAWorkShopActive).sum())
                            .countActiveCampaign(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountActiveCampaign).sum())
                            .countApplyCampaign(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountApplyCampaign).sum())
                            .countShareFromCreator(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountShareFromCreator).sum())
                            .countShareNotFromCreator(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountShareNotFromCreator).sum())
                            .countShare(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountShare).sum())
                            .countApplyFromCreator(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountApplyFromCreator).sum())
                            .countApplyNotFromCreator(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountApplyNotFromCreator).sum())
                            .countApplyUser(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountApplyUser).sum())
                            .countApplyTimes(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountApplyTimes).sum())
                            .countRegisterUser(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountRegisterUser).sum())
                            .countRegisterTimes(single.getValue().parallelStream().mapToLong(MaterialTemplateListDTO::getCountRegisterTimes).sum())
                            .build())
                .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

}
