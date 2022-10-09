package org.codeworks.web.toolsdashboardwebfacade.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardwebfacade.dto.aplus.*;
import org.codeworks.web.toolsdashboardwebfacade.dto.EventDTO;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeyRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeySingleRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;
import org.codeworks.web.toolsdashboardwebfacade.service.APlusService;
import org.codeworks.web.toolsdashboardwebfacade.service.BaiduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RequestMapping("download/aplus")
@RestController
public class DownloadAplusController extends ABWebController {

    @Value("${baidu.tongji.aplus.site-id}")
    Integer siteId;

    @Autowired
    APlusService aPlusService;

    @Autowired
    BaiduService baiduService;

    @GetMapping("test")
    @SneakyThrows(IOException.class)
    public void test(HttpServletResponse response,
                             @RequestParam String start,
                             @RequestParam String end){
        //localhost:53864/download/aplus/report?start=2017-12-12&end=2017-12-13
        String[] headWord = new String[]{
                "统计分类", "序号",
                "统计内容", "值"};

        Map<String, List<Object[]>> data = new LinkedHashMap();//无需填写序号,"##"会自动匹配为序号

        List<Object[]> one = Arrays.asList(
                new Object[]{"##","总访客数", 44},
                new Object[]{"##","创建画册人数", 44},
                new Object[]{"##","画册内容页访客数", 44});
        data.put("一、用户情况", one);

        List<Object[]> two = Arrays.asList(
                new Object[]{"##","分享总量", 44},
                new Object[]{"##","用户点赞总数", 44},
                new Object[]{"##","画册创建总数", 44},
                new Object[]{"##","画册制作量超过5个的人数", 44},
                new Object[]{"##","画册平均页数", 44},
                new Object[]{"##","制作总量最高的ID", 44}
        );
        data.put("二、用户活跃度", two);

        List<Object[]> three = Arrays.asList(
                new Object[]{"##","A+画册所有页面-PV",22},
                new Object[]{"##","A+画册所有页面-UV",22},
                new Object[]{"##","画册内容页-PV",22},
                new Object[]{"##","画册内容页-UV",22},
                new Object[]{"##","MySpace制作页-PV",22},
                new Object[]{"##","MySpace制作页-UV",22},
                new Object[]{"##","MySpace浏览页-PV",22},
                new Object[]{"##","MySpace浏览页-UV",22}
        );
        data.put("三、页面访问情况", three);

        List<Object[]> four = Arrays.asList(
                new Object[]{"##","室主分享带来访问-PV", 44},
                new Object[]{"##","室主分享带来访问-UV", 44},
                new Object[]{"##","非室主分享带来访问-PV", 44},
                new Object[]{"##","非室主分享带来访问-UV", 44},
                new Object[]{"##","引流线上工作室-PV", 44},
                new Object[]{"##","引流线上工作室-UV", 44}
        );
        data.put("四、画册引流情况", four);

        List<Object[]> five = Arrays.asList(
                new Object[]{"##","模板1",22},
                new Object[]{"##","模板2",22},
                new Object[]{"##","模板3",22},
                new Object[]{"##","模板4",22},
                new Object[]{"##","模板5",22},
                new Object[]{"##","模板6",22},
                new Object[]{"##","模板7",22},
                new Object[]{"##","模板8",22},
                new Object[]{"##","模板9",22},
                new Object[]{"##","模板10",22},
                new Object[]{"##","模板11",22},
                new Object[]{"##","模板12",22},
                new Object[]{"##","模板13",22},
                new Object[]{"##","模板14",22},
                new Object[]{"##","模板15",22},
                new Object[]{"##","模板16",22},
                new Object[]{"##","模板17",22},
                new Object[]{"##","模板18",22},
                new Object[]{"##","模板19",22},
                new Object[]{"##","模板20",22},
                new Object[]{"##","模板21",22},
                new Object[]{"##","模板22",22},
                new Object[]{"##","模板23",22},
                new Object[]{"##","模板24",22}
        );
        data.put("五、模板使用情况", five);

        List<Object[]> six = Arrays.asList(
                new Object[]{"##","无背景音乐",22},
                new Object[]{"##","光明希望2",22},
                new Object[]{"##","流行摇滚",22},
                new Object[]{"##","抒情高雅1",22},
                new Object[]{"##","积极向上2",22},
                new Object[]{"##","光明希望1",22},
                new Object[]{"##","青春动感",22},
                new Object[]{"##","积极向上1",22},
                new Object[]{"##","轻快活泼2",22},
                new Object[]{"##","轻快活泼1",22},
                new Object[]{"##","积极向上3",22},
                new Object[]{"##","抒情高雅2",22}
        );
        data.put("六、音乐使用情况", six);

        Map<String, Map<String, List<Object[]>>> dMap = new HashMap<>();
            dMap.put("【一期】A+画册用户基本数据列表", data);

        this.generate(response, "APlus_"+start+"_"+end, "A+画册 数据月报", headWord, dMap);
    }

    @GetMapping("report")
    @SneakyThrows(IOException.class)
    public void report(HttpServletResponse response,
                             @RequestParam String start,
                             @RequestParam String end){

        //localhost:9002/download/aplus/report?start=2017-12-12&end=2017-12-12
        String[] headWord = new String[]{
                "统计分类", "序号",
                "统计内容", "值"};

        Map<String, List<Object[]>> data = new LinkedHashMap();//无需填写序号,"##"会自动匹配为序号


        // 总访客数
        List<Object[]> sites = this.getBaiduSitePvAndUv(siteId,"总访客数", start, end, baiduService);
        Long totalUV = sites.parallelStream()
                .filter(s -> s[1].toString().indexOf("UV")!=-1)
                .mapToLong(s -> Long.valueOf(s[2].toString()))
                .findFirst().orElse(0L);

        // 画册内容页访客数
        List<EventStatisticsKeySingleRequest> totalContainPageUserEvents = new ArrayList<>();
        totalContainPageUserEvents.add(EventStatisticsKeySingleRequest.builder()
                .name("minisite页面").flag(ReportPropertyFlag.ACTION).build());

        EventStatisticsKeyRequest totalContainPageUserReq = EventStatisticsKeyRequest.builder()
                .events(totalContainPageUserEvents)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response totalContainPageUserResp = baiduService.key(totalContainPageUserReq, 1, 1000);
        JSONArray totalContainPageUserBody = JSONArray.parseArray(JSONArray.toJSONString(totalContainPageUserResp.get("body")));
        List<EventDTO> totalContainPageUserCollect =
                totalContainPageUserBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.toList());
        Long totalContainPageUser = totalContainPageUserCollect.parallelStream()
                .map(dto -> dto.getUv()).findFirst().orElse(0L);


        // 分享好友-回调 + 分享朋友圈-回调 = 分享总量
        List<EventStatisticsKeySingleRequest> totalShareEvents = new ArrayList<>();
        totalShareEvents.add(EventStatisticsKeySingleRequest.builder()
                .name("分享好友-回调").flag(ReportPropertyFlag.LABEL).build());
        totalShareEvents.add(EventStatisticsKeySingleRequest.builder()
                .name("分享朋友圈-回调").flag(ReportPropertyFlag.LABEL).build());

        EventStatisticsKeyRequest req = EventStatisticsKeyRequest.builder()
                .events(totalShareEvents)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response totalShareResp = baiduService.key(req, 1, 1000);
        JSONArray totalShareBody = JSONArray.parseArray(JSONArray.toJSONString(totalShareResp.get("body")));
        Long totalShare = totalShareBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.summingLong(EventDTO::getPv));


        // APlus
        Map<String, Object> aplusMap = (Map<String, Object>)aPlusService.organise(start, end).get("body");

        Map<String, Object> topMap = (Map<String, Object>) aplusMap.get("topCreator");
        APlusIndexDigestTopDTO top =
                APlusIndexDigestTopDTO.builder()
                        .ada(topMap.get("ada").toString())
                        .nickName(topMap.get("nickname").toString())
                        .openId(topMap.get("openId").toString())
                        .count(Long.valueOf(topMap.get("count").toString()))
                        .build();

        Long totalCreator = Long.valueOf(aplusMap.get("creatorCount").toString());
        Long totalUsedOverFiveTimes = Long.valueOf(aplusMap.get("creatorCountWithQuantity").toString());
        Double avgPage = (Double)aplusMap.get("avgPage");

        JSONObject galleryData = JSONObject.parseObject(JSONObject.toJSONString(
                aPlusService.galleryDataList(start, end, 1, 1000)));

        // 用户点赞总数
        Long likeCount = galleryData.getJSONArray("body").toJavaList(GalleryDTO.class)
                .parallelStream()
                .collect(Collectors.summingLong(GalleryDTO::getLikeCount));

        // 画册创建总数
        Long galleryCount = galleryData.getJSONArray("body").toJavaList(GalleryDTO.class)
                .parallelStream()
                .collect(Collectors.summingLong(GalleryDTO::getGalleryCount));

        List<Object[]> one = Arrays.asList(
                new Object[]{"##","总访客数", totalUV},
                new Object[]{"##","创建画册人数", totalCreator},
                new Object[]{"##","画册内容页访客数", totalContainPageUser});
        data.put("一、用户情况", one);

        List<Object[]> two = Arrays.asList(
                new Object[]{"##","分享总量", totalShare},
                new Object[]{"##","用户点赞总数", likeCount},
                new Object[]{"##","画册创建总数", galleryCount},
                new Object[]{"##","画册制作量超过5个的人数", totalUsedOverFiveTimes},
                new Object[]{"##","画册平均页数", String.valueOf(avgPage)},
                new Object[]{"##","制作总量最高的ID", top.toString()}
        );
        data.put("二、活动发布情况", two);




        List<EventStatisticsKeySingleRequest> threeRequestEvent = new ArrayList<>();
            threeRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                    .name("minisite页面").flag(ReportPropertyFlag.ACTION).build());
            threeRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                    .name("开始制作").flag(ReportPropertyFlag.ACTION).build());
            threeRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                    .name("MySpace浏览页").flag(ReportPropertyFlag.ACTION).build());

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
            String name;
            if (dto.getName().equals("minisite页面"))
                name = "画册内容页";
            else if (dto.getName().equals("开始制作"))
                name = "MySpace制作页";
            else name = dto.getName();

            three.add(new Object[]{"##", name + "-PV", dto.getPv()});
            three.add(new Object[]{"##", name + "-UV", dto.getUv()});
        });

        three.addAll(this.getBaiduSitePvAndUv(siteId, "A+画册所有页面",start, end, baiduService));

        data.put("三、页面访问情况", three);





        List<EventStatisticsKeySingleRequest> fourRequestEvent = new ArrayList<>();
        fourRequestEvent.add(EventStatisticsKeySingleRequest.builder()
                .name("查看移动工作室").flag(ReportPropertyFlag.LABEL).build());

        EventStatisticsKeyRequest fourReq = EventStatisticsKeyRequest.builder()
                .events(fourRequestEvent)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response fourResponse = baiduService.key(fourReq, 1, 100);
        JSONArray fourBody = JSONArray.parseArray(JSONArray.toJSONString(fourResponse.get("body")));

        List<EventDTO> fourCollect =
                fourBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.toList());

        List<Object[]> four = new ArrayList<>();
        fourCollect.stream().forEach(dto -> {
            String name;
            if (dto.getName().equals("查看移动工作室"))
                name = "引流线上工作室";
            else name = dto.getName();

            four.add(new Object[]{"##", name + "-PV", dto.getPv()});
            four.add(new Object[]{"##", name + "-UV", dto.getUv()});
        });

//        four.addAll(this.getBaiduVisitPvAndUv(siteId, "isShare", "室主分享带来访问", start, end, baiduService));
//        four.addAll(this.getBaiduVisitPvAndUv(siteId, "isTranspond", "非室主分享带来访问", start, end, baiduService));

        Map<Integer, List<APlusSourceBaiduDTO>> oneMap =
                this.getBaiduVisitPvAndUvBetween(siteId, "isShare", "室主分享带来访问", start, end, baiduService)
                        .parallelStream()
                        .map(objects -> APlusSourceBaiduDTO.builder()
                                .date((Integer) objects[0])
                                .name((String) objects[1])
                                .value((Long) objects[2])
                                .build()).collect(Collectors.groupingBy(APlusSourceBaiduDTO::getDate));

        Map<Integer, List<APlusSourceBaiduDTO>> twoMap =
                this.getBaiduVisitPvAndUvBetween(siteId, "isTranspond", "非室主分享带来访问", start, end, baiduService)
                        .parallelStream()
                        .map(objects -> APlusSourceBaiduDTO.builder()
                                .date((Integer) objects[0])
                                .name((String) objects[1])
                                .value((Long) objects[2])
                                .build()).collect(Collectors.groupingBy(APlusSourceBaiduDTO::getDate));

        Long shareFromABOPV = oneMap.values().parallelStream().flatMap(c -> c.parallelStream())
                .filter(dto -> dto.getName().indexOf("-PV")!=-1).collect(Collectors.summingLong(APlusSourceBaiduDTO::getValue));
        Long shareFromABOUV = oneMap.values().parallelStream().flatMap(c -> c.parallelStream())
                .filter(dto -> dto.getName().indexOf("-UV")!=-1).collect(Collectors.summingLong(APlusSourceBaiduDTO::getValue));
        Long shareNotFromABOPV = twoMap.values().parallelStream().flatMap(c -> c.parallelStream())
                .filter(dto -> dto.getName().indexOf("-PV")!=-1).collect(Collectors.summingLong(APlusSourceBaiduDTO::getValue));
        Long shareNotFromABOUV = twoMap.values().parallelStream().flatMap(c -> c.parallelStream())
                .filter(dto -> dto.getName().indexOf("-UV")!=-1).collect(Collectors.summingLong(APlusSourceBaiduDTO::getValue));

        four.add(new Object[]{"##", "室主分享带来访问-PV", shareFromABOPV});
        four.add(new Object[]{"##", "室主分享带来访问-UV", shareFromABOUV});
        four.add(new Object[]{"##", "非室主分享带来访问-PV", shareNotFromABOPV});
        four.add(new Object[]{"##", "非室主分享带来访问-UV", shareNotFromABOUV});
        data.put("四、画册引流情况", four);



        // 模板
        Response templateResponse = aPlusService.templateListData(start, end, 1, 100);
        JSONArray templateBody = JSONArray.parseArray(
                JSONArray.toJSONString(templateResponse.get("body")));
        List<APlusTemplateDTO> templateCollect =
                templateBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), APlusTemplateDTO.class))
                        .collect(Collectors.toList());

        List<Object[]> templates = new ArrayList<>();
        templateCollect.stream().forEach(dto ->
            templates.add(new Object[]{"##", "模板"+dto.getTemplateCode(), dto.getGalleryCount()}));
        data.put("五、模板使用情况", templates);


        Response musicResponse = aPlusService.musicListData(start, end, 1, 100);
        JSONArray musicBody = JSONArray.parseArray(
                JSONArray.toJSONString(musicResponse.get("body")));
        List<APlusMusicDTO> musicCollect =
                musicBody.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), APlusMusicDTO.class))
                        .collect(Collectors.toList());
        List<Object[]> musics = new ArrayList<>();
        musicCollect.stream().forEach(dto -> {
            String name;
            switch (dto.getMusicCode()) {
                case 0: name = "不添加背景音乐";break;
                case 1: name = "青春动感";break;
                case 2: name = "抒情高雅2";break;
                case 3: name = "抒情高雅1";break;
                case 4: name = "轻快活泼2";break;
                case 5: name = "轻快活泼1";break;
                case 6: name = "积极向上1";break;
                case 7: name = "积极向上2";break;
                case 8: name = "积极向上3";break;
                case 9: name = "光明希望1";break;
                case 10: name = "流行摇滚";break;
                case 11: name = "光明希望2";break;
                default: name = "";
            }
            musics.add(new Object[]{"##", name, dto.getUseCount()});
        });
        data.put("六、音乐使用情况", musics);



        Map<String, Map<String, List<Object[]>>> dMap = new HashMap<>();
        dMap.put("【一期】A+画册用户基本数据列表", data);

        this.generate(response, "APlus_"+start+"_"+end, "A+画册 数据月报", headWord, dMap);
    }

    @GetMapping("reportForAda")
    @SneakyThrows(IOException.class)
    public void reportForCreateAda(HttpServletResponse response,
                                   @RequestParam String start,
                                   @RequestParam String end){

        String[] headWord = new String[]{
                "类别", "序号",
                "ada"};

        Map<String, List<Object[]>> data = new LinkedHashMap();//无需填写序号,"##"会自动匹配为序号
        List<Object[]> adas = aPlusService.creatorAda(start, end)
                .parallelStream().map(ada -> new Object[]{"##", ada})
                .collect(Collectors.toList());
        data.put("ADA", adas);

        Map<String, Map<String, List<Object[]>>> dMap = new HashMap<>();
        dMap.put("创建画册的ada列表", data);

        this.generate(response, "APlus_Create_ADA_"+start+"_"+end, "ADA表", headWord, dMap);
    }
}
