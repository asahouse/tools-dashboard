package org.codeworks.web.toolsdashboardwebfacade.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardwebfacade.configuration.ConfigAAtoolInvitaion;
import org.codeworks.web.toolsdashboardwebfacade.configuration.ConfigAAtoolTemplate;
import org.codeworks.web.toolsdashboardwebfacade.dto.ConfigUsingStatus;
import org.codeworks.web.toolsdashboardwebfacade.dto.EventDTO;
import org.codeworks.web.toolsdashboardwebfacade.dto.FutureObject;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.aatool.RecordOpenIdAndAda;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeyRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeySingleRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;
import org.codeworks.web.toolsdashboardwebfacade.service.AAToolService;
import org.codeworks.web.toolsdashboardwebfacade.service.BaiduService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("download/aatool")
@RestController
@Import({ConfigAAtoolInvitaion.class, ConfigAAtoolTemplate.class})
public class DownloadAatoolController extends ABWebController {

    @Value("${baidu.tongji.aatool.site-id}")
    Integer siteId;

    private AAToolService aaToolService;
    private BaiduService baiduService;
    private final ConfigAAtoolInvitaion configAAtoolInvitaion;
    private final ConfigAAtoolTemplate configAAtoolTemplate;

    public DownloadAatoolController(AAToolService aaToolService, BaiduService baiduService,
                                    ConfigAAtoolInvitaion configAAtoolInvitaion,
                                    ConfigAAtoolTemplate configAAtoolTemplate) {
        this.aaToolService = aaToolService;
        this.baiduService = baiduService;
        this.configAAtoolInvitaion = configAAtoolInvitaion;
        this.configAAtoolTemplate = configAAtoolTemplate;
    }

    @GetMapping("test")
    @SneakyThrows(IOException.class)
    public void test(HttpServletResponse response,
                             @RequestParam String start,
                             @RequestParam String end){
        //localhost:9002/api/web/download/aatool/report?start=2017-12-12&end=2017-12-12
        String[] headWord = new String[]{
                "统计分类", "序号",
                "统计内容", "值"};

        Map<String, List<Object[]>> data = new LinkedHashMap();//无需填写序号,"##"会自动匹配为序号

        List<Object[]> one = Arrays.asList(
                new Object[]{"##","活动创建人", 44},
                new Object[]{"##","活动邀请人", 44},
                new Object[]{"##","活动参与者", 44},
                new Object[]{"##","用户总量", 44});
        data.put("一、用户情况", one);

        List<Object[]> two = Arrays.asList(
                new Object[]{"##","当月活动发布总数", 44},
                new Object[]{"##","关联线上工作室的当月发布活动", 44},
                new Object[]{"##","当期有效活动", 44},
                new Object[]{"##","关联线上工作室的活动", 44},
                new Object[]{"##","有产生报名的有效活动", 44}
        );
        data.put("二、活动发布情况", two);

        List<Object[]> three = Arrays.asList(
                new Object[]{"##","活动助手所有页面-PV",22},
                new Object[]{"##","活动助手所有页面-UV",22},
                new Object[]{"##","活动创建者访问活动页-PV",22},
                new Object[]{"##","活动创建者访问活动页-UV",22},
                new Object[]{"##","非活动创建者访问活动页-PV",22},
                new Object[]{"##","非活动创建者访问活动页-UV",22}
        );
        data.put("三、活动访问情况", three);

        List<Object[]> four = Arrays.asList(
                new Object[]{"##","活动分享次数-来自活动创始人", 44},
                new Object[]{"##","活动分享次数-来自非活动创建人", 44},
                new Object[]{"##","活动分享总量", 44},
                new Object[]{"##","活动报名人数-来自活动创始人", 44},
                new Object[]{"##","活动报名人数-来自非活动创始人", 44},
                new Object[]{"##","活动报名总次数", 44},
                new Object[]{"##","活动报名总人数", 44},
                new Object[]{"##","活动签到次数", 44},
                new Object[]{"##","活动签到人数", 44}
        );
        data.put("四、活动传播和参与", four);

        List<Object[]> five = Arrays.asList(
                new Object[]{"##","生成签到二维码",22},
                new Object[]{"##","生成活动二维码",22},
                new Object[]{"##","点击操作指南",22},
                new Object[]{"##","点击用户管理",22},
                new Object[]{"##","点击用户管理“编辑”",22},
                new Object[]{"##","点击移动工作室的人数",22},
                new Object[]{"##","点击移动工作室的次数",22},
                new Object[]{"##","通过邀请函参与活动的人次",22},
                new Object[]{"##","通过邀请链接参与活动的人次",22}
        );
        data.put("五、功能使用", five);

        List<Object[]> six = Arrays.asList(
                new Object[]{"##","邀请函生成总量",22},
                new Object[]{"##","邀请函使用-蓝色丝带",22},
                new Object[]{"##","邀请函使用-跑步",22},
                new Object[]{"##","邀请函使用-美妆（4.5上线）",22},
                new Object[]{"##","邀请函使用-美食",22},
                new Object[]{"##","邀请函使用-独角兽（5.4上线）",22},
                new Object[]{"##","邀请函使用-扑克牌（5.4上线）",22},
                new Object[]{"##","邀请函使用-XS邀请函（9.1上线）",22},

                new Object[]{"##","模板使用-美妈的早晨",22},
                new Object[]{"##","模板使用-亲子节日派对",22},
                new Object[]{"##","模板使用-针对白领人群的”减压派对”（9.1上线）",22},
                new Object[]{"##","模板使用-针对青年男女的”交友派对”（9.1上线）",22},
                new Object[]{"##","模板使用-针对运动人群的”比赛之夜”（9.1上线）",22},
                new Object[]{"##","模板使用-悦享美刻（10.20上线）",22},
                new Object[]{"##","模板使用-悦享食刻（10.20上线）",22},
                new Object[]{"##","模板使用-自定义模板",22},
                new Object[]{"##","模板使用-XS新口味上市",22},
                new Object[]{"##","模板使用总量",22}
        );
        data.put("六、素材统计", six);

        Map<String, Map<String, List<Object[]>>> dMap = new HashMap<>();
            dMap.put("安利活动助手数据测试报告", data);

        this.generate(response, "AATool_"+start+"_"+end, "活动助手数据月报", headWord, dMap);
    }

    @GetMapping("report")
    @SneakyThrows(IOException.class)
    public void report(HttpServletResponse response,
                             @RequestParam String start,
                             @RequestParam String end){

        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //localhost:9002/web/download/aatool/report?start=2017-12-12&end=2017-12-12
        String[] headWord = new String[]{
                "统计分类", "序号",
                "统计内容", "值"};

        Map<String, List<Object[]>> data = new LinkedHashMap();//无需填写序号,"##"会自动匹配为序号





        List<Object[]> one = Arrays.asList(
                new Object[]{"##","活动创建人", this.parseSingleResultCountToLong(
                        aaToolService.countCreator(startLocal, endLocal, false))},
                new Object[]{"##","活动邀请人", this.parseSingleResultCountToLong(
                        aaToolService.countInviter(startLocal, endLocal, false))},
                new Object[]{"##","活动参与者", this.parseSingleResultCountToLong(
                        aaToolService.countJoiner(startLocal, endLocal,false))},
                new Object[]{"##","用户总存量", this.parseSingleResultCountToLong(
                        aaToolService.countUser(startLocal, endLocal, false))}
                );
        data.put("一、用户情况", one);

        List<Object[]> two = Arrays.asList(
                new Object[]{"##","当月活动发布总数", this.parseMultiResultCountToLong(
                        aaToolService.countPublishCampaign(startLocal, endLocal, true))},
                new Object[]{"##","关联线上工作室的当月发布活动", this.parseMultiResultCountToLong(
                        aaToolService.countAAWorkShop(startLocal, endLocal, true))},
                new Object[]{"##","当期有效活动", this.parseSingleResultCountToLong(
                        aaToolService.countActiveCampaign(startLocal, endLocal))},
                new Object[]{"##","关联线上工作室的活动", this.parseSingleResultCountToLong(
                        aaToolService.countAAWorkShopActive(startLocal, endLocal))},
                new Object[]{"##","有产生报名的有效活动", this.parseMultiResultCountToLong(
                        aaToolService.countApplyCampaign(startLocal, endLocal, true))}
        );
        data.put("二、活动发布情况", two);




        List<EventStatisticsKeySingleRequest> threeRequestEvent = new ArrayList<>();
            threeRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                    .name("非室主访问活动页次数").flag(ReportPropertyFlag.LABEL).build());
            threeRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                    .name("室主访问活动页次数").flag(ReportPropertyFlag.LABEL).build());

        EventStatisticsKeyRequest threeReq = EventStatisticsKeyRequest.builder()
                .events(threeRequestEvent)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response threeResponse = baiduService.key(threeReq, 1, 100);
        JSONArray threeBody = JSONArray.parseArray(JSONArray.toJSONString(threeResponse.get("body")));

        List<EventDTO> threeCollect =
                threeBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.toList());

        List<Object[]> three = new ArrayList<>();
        threeCollect.stream().forEach(dto -> {
            boolean isInsert = false;
            String name = "";
            if (dto.getName().equals("非室主访问活动页次数")) {
                name = "非室主访问活动页";
                isInsert = true;
            }else if (dto.getName().equals("室主访问活动页次数")) {
                name = "室主访问活动页";
                isInsert = true;
            }

            if (isInsert) {
                three.add(new Object[]{"##", name + "-PV", dto.getPv()});
                three.add(new Object[]{"##", name + "-UV", dto.getUv()});
            }
        });

        three.addAll(this.getBaiduSitePvAndUv(siteId, "活动助手所有页面", start, end, baiduService));

        data.put("三、活动访问情况", three);







        Long countShareFromABO = this.parseMultiResultCountToLong(
                aaToolService.countABOShareTimes(startLocal, endLocal, true));
        Long countShareFromInvite = this.parseMultiResultCountToLong(
                aaToolService.countInviteShareTimes(startLocal, endLocal, true));
        Long countShareFromVisitor = this.parseMultiResultCountToLong(
                aaToolService.countVisitorShareTimes(startLocal, endLocal, true));
        Long countShareNotFromABO = BigDecimal.valueOf(countShareFromInvite)
                .add(BigDecimal.valueOf(countShareFromVisitor)).longValue();
        Long countShareTotal = BigDecimal.valueOf(countShareFromABO)
                .add(BigDecimal.valueOf(countShareNotFromABO)).longValue();


        Long countApplyFromABO = this.parseMultiResultCountToLong(
                aaToolService.countChannelApply(startLocal, endLocal, true, true));
        Long countApplyNotFromABO = this.parseMultiResultCountToLong(
                aaToolService.countChannelApply(startLocal, endLocal, true, false));
        Long countApplyTotalTimes = this.parseMultiResultCountToLong(
                aaToolService.countApply(startLocal, endLocal, true));
        Long countApplyTotalUser = BigDecimal.valueOf(countApplyFromABO)
                .add(BigDecimal.valueOf(countApplyNotFromABO)).longValue();


        Long countRegisterTotalUser = this.parseMultiResultCountToLong(
                aaToolService.countRegister(startLocal, endLocal, true));
        Long countRegisterTotalTimes = this.parseMultiResultCountToLong(
                aaToolService.countRegisterTimes(startLocal, endLocal, true));

        List<Object[]> four = Arrays.asList(
                new Object[]{"##","活动分享次数-来自活动创始人", countShareFromABO},
                new Object[]{"##","活动分享次数-来自非活动创建人", countShareNotFromABO},
                new Object[]{"##","活动分享总量", countShareTotal},
                new Object[]{"##","活动报名人数-来自活动创始人", countApplyFromABO},
                new Object[]{"##","活动报名人数-来自非活动创始人", countApplyNotFromABO},
                new Object[]{"##","活动报名总次数", countApplyTotalTimes},
                new Object[]{"##","活动报名总人数", countApplyTotalUser},
                new Object[]{"##","活动签到次数", countRegisterTotalTimes},
                new Object[]{"##","活动签到人数", countRegisterTotalUser}
        );
        data.put("四、活动传播和参与", four);







        List<EventStatisticsKeySingleRequest> fiveRequestEvent = new ArrayList<>();
        fiveRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("生成签到码").flag(ReportPropertyFlag.LABEL).build());
        fiveRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("创建活动报名码").flag(ReportPropertyFlag.ACTION).build());
        fiveRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("首页-操作指南").flag(ReportPropertyFlag.LABEL).build());
        fiveRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("活动管理-用户管理").flag(ReportPropertyFlag.LABEL).build());
        fiveRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("活动用户管理-编辑").flag(ReportPropertyFlag.LABEL).build());
        fiveRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("顾客点击移动工作室次数").flag(ReportPropertyFlag.LABEL).build());

        EventStatisticsKeyRequest fiveReq = EventStatisticsKeyRequest.builder()
                .events(fiveRequestEvent)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response fiveResponse = baiduService.key(fiveReq, 1, 100);
        JSONArray fiveBody = JSONArray.parseArray(JSONArray.toJSONString(fiveResponse.get("body")));

        List<EventDTO> fiveCollect =
                fiveBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.toList());

        List<Object[]> five = new ArrayList<>();
        fiveCollect.stream().forEach(dto -> {
            if (dto.getName().equals("顾客点击移动工作室次数")) {
                five.add(new Object[]{"##", "点击移动工作室的人数", dto.getUv()});
                five.add(new Object[]{"##", "点击移动工作室的次数", dto.getPv()});
            } else five.add(new Object[]{"##", dto.getName(), dto.getPv()});
        });

        five.addAll(this.filterBaiduPvOrUv(this.getBaiduVisitPvAndUv(siteId, "isInvitationCode", "通过邀请函参与活动的人次",
                start, end, baiduService), false));
        five.addAll(this.filterBaiduPvOrUv(this.getBaiduVisitPvAndUv(siteId, "isAboShare", "通过邀请链接参与活动的人次",
                start, end, baiduService), false));

        data.put("五、功能使用", five);






        List<EventStatisticsKeySingleRequest> sixRequestEvent = new ArrayList<>();

        //邀请函
        configAAtoolInvitaion.getConfigObjects().stream()
                .filter(o -> o.getStatus().equals(ConfigUsingStatus.ACTIVATION))
                .forEachOrdered(o ->
                        sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                                .name(o.getName()).flag(o.getFlag()).build())
                );

        //模板
        configAAtoolTemplate.getConfigObjects().stream()
                .filter(o -> o.getStatus().equals(ConfigUsingStatus.ACTIVATION))
                .forEachOrdered(o ->
                        sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                                .name(o.getName()).flag(o.getFlag()).build())
                );

//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-美妆邀请函").flag(ReportPropertyFlag.LABEL).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-蓝丝带").flag(ReportPropertyFlag.LABEL).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-跑步").flag(ReportPropertyFlag.LABEL).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-独角兽").flag(ReportPropertyFlag.LABEL).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-美食").flag(ReportPropertyFlag.LABEL).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-XS邀请函").flag(ReportPropertyFlag.LABEL).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("邀请函-扑克牌").flag(ReportPropertyFlag.LABEL).build());
//
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("美妈的早晨").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("亲子节日派对").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("针对运动人群的“比赛之夜”").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("针对青年男女的“交友派对”").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("针对白领人群的“减压派对”").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("美容主题社群系列套装《悦享“美”刻》").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("美食主题社群系列套装《悦享“食”刻》").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("自定义").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("这个圣诞有你不一YOUNG").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("这个新年有你不一YOUNG").flag(ReportPropertyFlag.ACTION).build());
//            sixRequestEvent.add(EventStatisticsKeySingleRequest.builder()
//                    .name("XS新口味上市").flag(ReportPropertyFlag.ACTION).build());

        EventStatisticsKeyRequest sixReq = EventStatisticsKeyRequest.builder()
                .events(sixRequestEvent)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response sixResponse = baiduService.key(sixReq, 1, 100);
        JSONArray sixBody = JSONArray.parseArray(JSONArray.toJSONString(sixResponse.get("body")));

        List<Object[]> six = sixBody.parallelStream()
                .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                .map(dto -> new Object[]{"##", dto.getName(), dto.getPv()})
                .collect(Collectors.toList());

        data.put("六、素材统计", six);



        Map<String, Map<String, List<Object[]>>> dMap = new HashMap<>();
        dMap.put("安利活动助手数据报告", data);

        this.generate(response, "AATool_"+start+"_"+end, "活动助手数据月报", headWord, dMap);
    }

    @GetMapping("reportForOpenIdAndAda")
    @SneakyThrows(IOException.class)
    public void reportForCreateOpenIdAndAda(HttpServletResponse response,
                                            @RequestParam String start,
                                            @RequestParam String end){

        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        List<FutureObject<List<RecordOpenIdAndAda>>> futureObjects = new ArrayList<>();

        futureObjects.add(FutureObject.<List<RecordOpenIdAndAda>>builder()
                .future(CompletableFuture.supplyAsync(() -> aaToolService.countCreateOpenIdAndAda(startLocal, endLocal)))
                .name("创建")
                .build());

        futureObjects.add(FutureObject.<List<RecordOpenIdAndAda>>builder()
                .future(CompletableFuture.supplyAsync(() -> aaToolService.countApplyOpenIdAndAda(startLocal, endLocal)))
                .name("报名")
                .build());

        futureObjects.add(FutureObject.<List<RecordOpenIdAndAda>>builder()
                .future(CompletableFuture.supplyAsync(() -> aaToolService.countShareOpenIdAndAda(startLocal, endLocal)))
                .name("分享")
                .build());

        Map<String, List<RecordOpenIdAndAda>> result = this.sequence(futureObjects).join();


        String[] headWord = new String[]{
                "类别", "序号",
                "OpenId", "Ada"};

        Map<String, Map<String, List<Object[]>>> dMap = new HashMap<>();
        result.entrySet().parallelStream().forEach(single -> {
            Map<String, List<Object[]>> data = new HashMap<>();
            data.put("数据列", single.getValue().parallelStream().map(
                    r -> new Object[]{"##", r.getOpenId(), r.getAda()}).collect(Collectors.toList()));
            dMap.put(single.getKey(), data);
        });

        this.generate(response, "AATool_OpenIdAndADA_"+start+"_"+end, "对应表", headWord, dMap);
    }

}
