package org.codeworks.web.toolsdashboardaatool.controller;

import org.codeworks.web.toolsdashboardaatool.dto.campaign.CampaignListDTO;
import org.codeworks.web.toolsdashboardaatool.service.CampaignService;
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

@RequestMapping("campaign")
@RestController
public class CampaignController {

    @Autowired
    CampaignService campaignService;

    @GetMapping("list")
    public Response campaignList(@RequestParam String start,
                         @RequestParam String end,
                         @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<CampaignListDTO> result = campaignService.getCampaignList(
                LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .parallelStream().sorted(
                        Comparator.comparing(CampaignListDTO::getDate).reversed())
                .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

}
