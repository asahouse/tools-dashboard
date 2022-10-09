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

    //数据首页摘要
    @ApiOperation(value = "活动助手 - 首页 - 摘要", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String")
    })
    @GetMapping("index/digest")
    public Response digest(@RequestParam String start,
                           @RequestParam String end){
        return aaToolService.digest(start, end);
    }

    //数据首页列表
    @ApiOperation(value = "活动助手 - 首页 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
    })
    @GetMapping("index/list")
    public Response list(@RequestParam String start,
                         @RequestParam String end,
                         @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                         @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aaToolService.list(start, end, pageIndex, pageSize);
    }

    //活动分析
    @ApiOperation(value = "活动助手 - 活动分析 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
    })
    @GetMapping("campaignStatistics/list")
    public Response campaignStatistics(@RequestParam String start,
                                       @RequestParam String end,
                                       @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                       @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aaToolService.campaignList(start, end, pageIndex, pageSize);
    }

    //页面分析 - 页面PV/UV数据
    @ApiOperation(value = "活动助手 - 页面分析 - 页面PV/UV数据 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "gran", value = "数据维度: DAY / HOUR, 大小写敏感", required = true, dataType = "String"),
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
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

    //来源分析 / 活动分享
    @ApiOperation(value = "活动助手 - 来源分析 - 活动分享 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/campaign/share/list")
    public Response sourceStatisticsCampaignShare(@RequestParam String start,
                                                  @RequestParam String end,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aaToolService.share(start, end, pageIndex, pageSize);
    }

    //来源分析 / 活动报名
    @ApiOperation(value = "活动助手 - 来源分析 - 活动报名 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/campaign/apply/list")
    public Response sourceStatisticsCampaignApply(@RequestParam String start,
                                                  @RequestParam String end,
                                                  @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                  @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        return aaToolService.apply(start, end, pageIndex, pageSize);
    }

    //来源分析 / 活动签到
    @ApiOperation(value = "活动助手 - 来源分析 - 活动签到 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/campaign/register/list")
    public Response sourceStatisticsCampaignRegister(@RequestParam String start,
                                                     @RequestParam String end,
                                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        return aaToolService.register(start, end, pageIndex, pageSize);
    }

    //目标分析
    @ApiOperation(value = "活动助手 - 目标分析 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String")
    })
    @GetMapping("targetStatistics/list")
    public Response targetStatistics(@RequestParam String start,
                                     @RequestParam String end){
        List<EventStatisticsKeySingleRequest> events = new ArrayList<>();
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("生成签到码").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("创建活动报名码").flag(ReportPropertyFlag.ACTION).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("首页-操作指南").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("活动管理-用户管理").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("活动用户管理-编辑").flag(ReportPropertyFlag.LABEL).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("顾客点击移动工作室次数").flag(ReportPropertyFlag.LABEL).build());

        EventStatisticsKeyRequest req = EventStatisticsKeyRequest.builder()
                .events(events)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        //得出事件的UV/PV
        Response eventResp = baiduService.key(req, 1, 100);
        //抽取关键字段
        List<Map<String, Object>> bodyMap = ((List<LinkedHashMap>) eventResp.get("body")).parallelStream()
                .map(single -> {
                    String name;
                    if ("顾客点击移动工作室次数".equals(single.get("name")))
                        name = "顾客点击移动工作室";
                    else name = single.get("name").toString();

                    Map<String, Object> one = new HashMap<>();
                    one.put("uv",single.get("uv"));
                    one.put("pv",single.get("pv"));
                    one.put("name",name);
                    return one;
                }).collect(Collectors.toList());

        //获取受访页面的UV/PV, 并融合到body中
        bodyMap.add(this.getVisitPageSumMap(siteId, start, end, "isInvitationCode", "通过邀请函参与活动的人次", baiduService));
        bodyMap.add(this.getVisitPageSumMap(siteId, start, end, "isAboShare", "通过邀请链接参与活动的人次", baiduService));

        eventResp.add("body", bodyMap);

        return eventResp;
    }

    //素材统计 / 模板素材

    /**
     '0':'自定义',
     '1':'气质女王秀',
     '2':'美妈的早晨',
     '3':'亲子节日派对',
     '4':'针对白领人群的“减压派对”',
     '5':'针对青年男女的“交友派对”',
     '6':'针对运动人群的“比赛之夜”',
     '7':'美容主题社群系列套装《悦享“美”刻》',
     '8':'美食主题社群系列套装《悦享“食”刻》',
     '9':'这个圣诞有你不一YOUNG',
     '10':'这个新年有你不一YOUNG',
     '11':'2018  “缤纷夏日FUN享派”',
     */
    @ApiOperation(value = "活动助手 - 素材统计 - 模板素材 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
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

        //让配置中的文字设置入返回集合中
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

    //素材统计 / 邀请函素材
    @ApiOperation(value = "活动助手 - 素材统计 - 邀请函素材 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
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
