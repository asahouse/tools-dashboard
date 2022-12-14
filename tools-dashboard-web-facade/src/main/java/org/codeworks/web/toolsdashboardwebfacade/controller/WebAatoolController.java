package org.codeworks.web.toolsdashboardwebfacade.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.java.Log;
import org.codeworks.web.toolsdashboardwebfacade.configuration.ConfigAAtoolInvitaion;
import org.codeworks.web.toolsdashboardwebfacade.configuration.ConfigAAtoolTemplate;
import org.codeworks.web.toolsdashboardwebfacade.dto.ConfigUsingStatus;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeyRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeySingleRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.PageStatisticsTotalRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyGran;
import org.codeworks.web.toolsdashboardwebfacade.service.AAToolService;
import org.codeworks.web.toolsdashboardwebfacade.service.BaiduService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@Log
@RequestMapping("aatool")
@RestController
@Import({ConfigAAtoolInvitaion.class, ConfigAAtoolTemplate.class})
public class WebAatoolController extends ABWebController{

    @Value("${baidu.tongji.aatool.site-id}")
    Integer siteId;

    private AAToolService aaToolService;
    private BaiduService baiduService;
    private final ConfigAAtoolInvitaion configAAtoolInvitaion;
    private final ConfigAAtoolTemplate configAAtoolTemplate;

    public WebAatoolController(AAToolService aaToolService, BaiduService baiduService,
                               ConfigAAtoolTemplate configAAtoolTemplate,
                               ConfigAAtoolInvitaion configAAtoolInvitaion) {
        this.aaToolService = aaToolService;
        this.baiduService = baiduService;
        this.configAAtoolTemplate = configAAtoolTemplate;
        this.configAAtoolInvitaion = configAAtoolInvitaion;
    }

    //??????????????????
    @ApiOperation(value = "???????????? - ?????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String")
    })
    @GetMapping("index/digest")
    public Response digest(@RequestParam String start,
                           @RequestParam String end){
        return aaToolService.digest(start, end);
    }

    //??????????????????
    @ApiOperation(value = "???????????? - ?????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("index/list")
    public Response list(@RequestParam String start,
                         @RequestParam String end,
                         @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aaToolService.list(start, end, pageIndex, pageSize);
    }

    //????????????
    @ApiOperation(value = "???????????? - ???????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("campaignStatistics/list")
    public Response campaignStatistics(@RequestParam String start,
                                       @RequestParam String end,
                                       @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aaToolService.campaignList(start, end, pageIndex, pageSize);
    }

    //???????????? - ??????PV/UV??????
    @ApiOperation(value = "???????????? - ???????????? - ??????PV/UV?????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gran", value = "????????????: DAY / HOUR, ???????????????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("pageStatistics/list")
    public Response pageStatisticsPageData(@RequestParam ReportPropertyGran gran,
                                           @RequestParam String start,
                                           @RequestParam String end,
                                           @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                           @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        PageStatisticsTotalRequest req = PageStatisticsTotalRequest.builder()
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .gran(gran)
                .site_id(siteId).build();

        return baiduService.total(req, pageIndex, pageSize);
    }

    //???????????? / ????????????
    @ApiOperation(value = "???????????? - ???????????? - ???????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/campaign/share/list")
    public Response sourceStatisticsCampaignShare(@RequestParam String start,
                                                  @RequestParam String end,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aaToolService.share(start, end, pageIndex, pageSize);
    }

    //???????????? / ????????????
    @ApiOperation(value = "???????????? - ???????????? - ???????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/campaign/apply/list")
    public Response sourceStatisticsCampaignApply(@RequestParam String start,
                                                  @RequestParam String end,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        return aaToolService.apply(start, end, pageIndex, pageSize);
    }

    //???????????? / ????????????
    @ApiOperation(value = "???????????? - ???????????? - ???????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/campaign/register/list")
    public Response sourceStatisticsCampaignRegister(@RequestParam String start,
                                                     @RequestParam String end,
                                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        return aaToolService.register(start, end, pageIndex, pageSize);
    }

    //????????????
    @ApiOperation(value = "???????????? - ???????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String")
    })
    @GetMapping("targetStatistics/list")
    public Response targetStatistics(@RequestParam String start,
                                     @RequestParam String end){
        List<EventStatisticsKeySingleRequest> events = new ArrayList<>();
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("???????????????").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("?????????????????????").flag(ReportPropertyFlag.ACTION).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("??????-????????????").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("????????????-????????????").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("??????????????????-??????").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("?????????????????????????????????").flag(ReportPropertyFlag.LABEL).build());

        EventStatisticsKeyRequest req = EventStatisticsKeyRequest.builder()
                .events(events)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        //???????????????UV/PV
        Response eventResp = baiduService.key(req, 1, 100);
        //??????????????????
        List<Map<String, Object>> bodyMap = ((List<LinkedHashMap>) eventResp.get("body")).parallelStream()
                .map(single -> {
                    String name;
                    if ("?????????????????????????????????".equals(single.get("name")))
                        name = "???????????????????????????";
                    else name = single.get("name").toString();

                    Map<String, Object> one = new HashMap<>();
                    one.put("uv",single.get("uv"));
                    one.put("pv",single.get("pv"));
                    one.put("name",name);
                    return one;
                }).collect(Collectors.toList());

        //?????????????????????UV/PV, ????????????body???
        bodyMap.add(this.getVisitPageSumMap(siteId, start, end, "isInvitationCode", "????????????????????????????????????", baiduService));
        bodyMap.add(this.getVisitPageSumMap(siteId, start, end, "isAboShare", "???????????????????????????????????????", baiduService));

        eventResp.add("body", bodyMap);

        return eventResp;
    }

    //???????????? / ????????????

    /**
     '0':'?????????',
     '1':'???????????????',
     '2':'???????????????',
     '3':'??????????????????',
     '4':'???????????????????????????????????????',
     '5':'???????????????????????????????????????',
     '6':'???????????????????????????????????????',
     '7':'??????????????????????????????????????????????????????',
     '8':'??????????????????????????????????????????????????????',
     '9':'????????????????????????YOUNG',
     '10':'????????????????????????YOUNG',
     '11':'2018  ???????????????FUN?????????',
     */
    @ApiOperation(value = "???????????? - ???????????? - ???????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("materialStatistics/template/list")
    public Response materialStatisticsTemplate(@RequestParam String start,
                                               @RequestParam String end,
                                               @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                               @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<Integer> templateIds = configAAtoolTemplate.getConfigObjects().stream()
                .filter(o -> o.getStatus().equals(ConfigUsingStatus.ACTIVATION))
                .map(o -> o.getId()).collect(Collectors.toList());
        Response response = aaToolService.templateList(templateIds,start, end, pageIndex, pageSize);

        //?????????????????????????????????????????????
        List<LinkedHashMap> body = (List<LinkedHashMap>) response.get("body");
        body.stream().forEach(i -> {
            String beanName = configAAtoolTemplate.getConfigObjects().stream()
                    .filter(b -> b.getId().equals(i.get("templateId")))
                    .findFirst().get().getName();
            i.put("name", beanName);
        });
        response.replace("body", body);

        return response;
    }

    //???????????? / ???????????????
    @ApiOperation(value = "???????????? - ???????????? - ??????????????? - ??????", notes = "?????????????????????????????????")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "????????????(????????????)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "??????", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "????????????", required = true, dataType = "String")
    })
    @GetMapping("materialStatistics/invitationCard/list")
    public Response materialStatisticsInvitationCard(@RequestParam String start,
                                                     @RequestParam String end,
                                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        List<EventStatisticsKeySingleRequest> events = new ArrayList<>();
        configAAtoolInvitaion.getConfigObjects().stream()
                .filter(o -> o.getStatus().equals(ConfigUsingStatus.ACTIVATION))
                .forEachOrdered(o ->
                    events.add(EventStatisticsKeySingleRequest.builder()
                            .name(o.getName()).flag(o.getFlag()).build())
                );

        EventStatisticsKeyRequest req = EventStatisticsKeyRequest.builder()
                .events(events)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        return baiduService.key(req, pageIndex, pageSize);
    }





}
