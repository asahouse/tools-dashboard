package org.codeworks.web.toolsdashboardwebfacade.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.codeworks.web.toolsdashboardwebfacade.dto.EventDTO;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.aplus.*;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeyRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeySingleRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.PageStatisticsTotalRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyFlag;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.enumtype.ReportPropertyGran;
import org.codeworks.web.toolsdashboardwebfacade.service.APlusService;
import org.codeworks.web.toolsdashboardwebfacade.service.BaiduService;
import org.codeworks.web.toolsdashboardwebfacade.utils.ArrayPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("aplus")
@RestController
public class WebAplusController extends ABWebController {

    @Value("${baidu.tongji.aplus.site-id}")
    Integer siteId;

    @Autowired
    APlusService aPlusService;

    @Autowired
    BaiduService baiduService;

    /**
     * 获取A+画册首页汇总数据
     * @param start
     * @param end
     * @return
     */
    @ApiOperation("A+画册 - 首页 - 摘要")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query")
    })
    @GetMapping("index/digest")
    public Response organise(@RequestParam String start,
                             @RequestParam String end){

        // 总访客数
        List<Object[]> sites = this.getBaiduSitePvAndUv(siteId,"总访客数", start, end, baiduService);
        Long totalUV = sites.parallelStream()
                .filter(s -> s[1].toString().indexOf("UV")!=-1)
                .mapToLong(s -> Long.valueOf(s[2].toString()))
                .findFirst().orElse(0L);

        // 画册内容页访客数
        List<EventStatisticsKeySingleRequest> events = new ArrayList<>();
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("minisite页面").flag(ReportPropertyFlag.ACTION).build());

        EventStatisticsKeyRequest req = EventStatisticsKeyRequest.builder()
                .events(events)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response resp = baiduService.key(req, 1, 100);
        JSONArray body = JSONArray.parseArray(JSONArray.toJSONString(resp.get("body")));
        List<EventDTO> collect =
                body.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.toList());
        Long totalContainPageUser = collect.parallelStream().map(dto -> dto.getUv()).findFirst().orElse(0L);

        // APlus
        Map<String, Object> aplusMap = (Map<String, Object>)aPlusService.organise(start, end).get("body");

        Long totalCreator = Long.valueOf(aplusMap.get("creatorCount").toString());
        Long totalUsedOverFiveTimes = Long.valueOf(aplusMap.get("creatorCountWithQuantity").toString());
        Double avgPage = (Double)aplusMap.get("avgPage");

        Map<String, Object> topMap = (Map<String, Object>) aplusMap.get("topCreator");
        APlusIndexDigestTopDTO top =
                APlusIndexDigestTopDTO.builder()
                .ada(topMap.get("ada").toString())
                .nickName(topMap.get("nickname").toString())
                .openId(topMap.get("openId").toString())
                .count(Long.valueOf(topMap.get("count").toString()))
                .build();

        APlusIndexDigestDTO result = APlusIndexDigestDTO.builder()
                .totalUsedOverFiveTimes(totalUsedOverFiveTimes)
                .totalCreator(totalCreator)
                .totalVisitor(totalUV)
                .totalContainPageUser(totalContainPageUser)
                .top(top)
                .avgPage(avgPage)
                .build();
        return Response.ok("body", result);
    }

    /**
     * 获取画册基础数据列表
     * @param start
     * @param end
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation("A+画册 - 首页 - 列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("index/list")
    public Response organiseDataList(@RequestParam String start,
                                     @RequestParam String end,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Set<Integer> durationDates = this.parseLocalDateToStringSet(startLocal, endLocal);

        //总访客数
        List<Object[]> sites = this.getBaiduSitePvAndUvBetween(siteId, "总访客数", start, end, baiduService);
        Map<Integer, List<APlusIndexDigestListSiteDTO>> siteMap =
                sites.parallelStream()
                        .filter(s -> s[1].toString().indexOf("UV")!=-1)
                        .map(s ->
                        APlusIndexDigestListSiteDTO.builder()
                            .date(Integer.valueOf(s[0].toString()))
                            .name(s[1].toString())
                            .value(Long.valueOf(s[2].toString()))
                            .build())
                        .collect(Collectors.groupingBy(APlusIndexDigestListSiteDTO::getDate));

        //画册内容页访客数
        EventStatisticsKeySingleRequest one =
                EventStatisticsKeySingleRequest.builder().name("minisite页面").flag(ReportPropertyFlag.ACTION).build();
        Map<Integer, List<EventDTO>> oneMap =
                this.buildDTOBetween(siteId, one, startLocal, endLocal).parallelStream().collect(
                        Collectors.groupingBy(EventDTO::getStart_date));

        JSONObject aplusData = JSONObject.parseObject(JSONObject.toJSONString(
                aPlusService.organiseDataList(start, end, pageIndex, pageSize)));

        Map<Integer, List<EventDTO>> aplusMap = aplusData.getJSONArray("body").toJavaList(OrganiseDTO.class)
                .parallelStream().map(dto -> EventDTO.builder()
                        .name("creatorCount").uv(dto.getCreatorCount()).start_date(dto.getDate()).build())
                .collect(Collectors.groupingBy(EventDTO::getStart_date));

        List<APlusIndexDigestListDTO> result = durationDates.parallelStream().sorted(Comparator.reverseOrder()).map(date -> {

            Long totalContainPageUser = oneMap.containsKey(date) ? oneMap.get(date).get(0).getUv() : 0L;
            Long totalCreator = aplusMap.containsKey(date) ? aplusMap.get(date).get(0).getUv() : 0L;
            Long totalVisitor = siteMap.containsKey(date) ? siteMap.get(date).get(0).getValue() : 0L;

            APlusIndexDigestListDTO dto = APlusIndexDigestListDTO.builder()
                    .totalContainPageUser(totalContainPageUser)
                    .totalCreator(totalCreator)
                    .totalVisitor(totalVisitor)
                    .date(date)
                    .build();
            return dto;
        }).collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());

    }

    /**
     * 获取画册分析数据列表
     * @param start
     * @param end
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation("A+画册 - 画册数据分析 - 列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("galleryStatistics/list")
    public Response galleryDataList(@RequestParam String start,
                                    @RequestParam String end,
                                    @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                    @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Set<Integer> durationDates = this.parseLocalDateToStringSet(startLocal, endLocal);

        // 分享好友-回调 + 分享朋友圈-回调 = 分享总量
        EventStatisticsKeySingleRequest two =
                EventStatisticsKeySingleRequest.builder().name("分享好友-回调").flag(ReportPropertyFlag.LABEL).build();
        Map<Integer, List<EventDTO>> twoMap =
                this.buildDTOBetween(siteId, two, startLocal, endLocal).parallelStream().collect(
                        Collectors.groupingBy(EventDTO::getStart_date));

        EventStatisticsKeySingleRequest three =
                EventStatisticsKeySingleRequest.builder().name("分享朋友圈-回调").flag(ReportPropertyFlag.LABEL).build();
        Map<Integer, List<EventDTO>> threeMap =
                this.buildDTOBetween(siteId, three, startLocal, endLocal).parallelStream().collect(
                        Collectors.groupingBy(EventDTO::getStart_date));

        JSONObject aplusData = JSONObject.parseObject(JSONObject.toJSONString(
                aPlusService.galleryDataList(start, end, pageIndex, pageSize)));

        Map<Integer, List<GalleryDTO>> aplusMap = aplusData.getJSONArray("body").toJavaList(GalleryDTO.class)
                .parallelStream()
                .collect(Collectors.groupingBy(GalleryDTO::getDate));

        List<APlusGalleryListDTO> result = durationDates.parallelStream().sorted(Comparator.reverseOrder()).map(date -> {

            Long shareTotal = ( twoMap.containsKey(date) && threeMap.containsKey(date) ) ?
                    BigDecimal.valueOf(twoMap.get(date).get(0).getUv())
                            .add(BigDecimal.valueOf(threeMap.get(date).get(0).getUv())).longValue() : 0L;

            Long likeCount = aplusMap.containsKey(date) ? aplusMap.get(date).get(0).getLikeCount() : 0L;
            Long galleryCount = aplusMap.containsKey(date) ? aplusMap.get(date).get(0).getGalleryCount() : 0L;

            return APlusGalleryListDTO.builder()
                    .shareTotal(shareTotal)
                    .likeCount(likeCount)
                    .galleryCount(galleryCount)
                    .date(date).build();

        }).collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }


    /**
     * 页面分析 - 页面PV/UV数据
     * @param gran
     * @param start
     * @param end
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "A+画册 - 页面分析 - 页面PV/UV数据 - 列表", notes = "按日期返回期间所有数据")
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

    /**
     * 关键页面分析
     * @param start
     * @param end
     * @return
     */
    @ApiOperation(value = "A+画册 - 关键页面分析 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String")
    })
    @GetMapping("keyPageStatistics/list")
    public Response keyPageStatistics(@RequestParam String start,
                                     @RequestParam String end){
        List<EventStatisticsKeySingleRequest> events = new ArrayList<>();
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("minisite页面").flag(ReportPropertyFlag.ACTION).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("开始制作").flag(ReportPropertyFlag.ACTION).build());
        events.add(EventStatisticsKeySingleRequest.builder()
                .name("MySpace浏览页").flag(ReportPropertyFlag.ACTION).build());

        EventStatisticsKeyRequest req = EventStatisticsKeyRequest.builder()
                .events(events)
                .start_date(parseDateToInteger(start))
                .end_date(parseDateToInteger(end))
                .site_id(siteId).build();

        Response resp = baiduService.key(req, 1, 100);
        JSONArray body = JSONArray.parseArray(JSONArray.toJSONString(resp.get("body")));
        List<EventDTO> collect =
                body.parallelStream()
                        .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class))
                        .collect(Collectors.toList());

        List<EventDTO> result = collect.stream().map(dto -> {
            String name;
            if (dto.getName().equals("minisite页面"))
                name = "画册内容页";
            else if (dto.getName().equals("开始制作"))
                name = "MySpace制作页";
            else name = dto.getName();
            return EventDTO.builder().name(name).pv(dto.getPv()).uv(dto.getUv()).build();
        }).collect(Collectors.toList());

        return Response.ok("body", result);
    }

    /**
     * 传播分析
     * @param start
     * @param end
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "A+画册 - 传播分析 - 列表", notes = "按日期返回期间所有数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "start", value = "开始日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "end", value = "结束日期(横杠分隔)", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageIndex", value = "页码", required = true, dataType = "String"),
            @ApiImplicitParam(name = "pageSize", value = "每页个数", required = true, dataType = "String")
    })
    @GetMapping("sourceStatistics/list")
    public Response sourceStatistics(@RequestParam String start,
                                     @RequestParam String end,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        LocalDate startLocal = LocalDate.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endLocal = LocalDate.parse(end, DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        Set<Integer> durationDates = this.parseLocalDateToStringSet(startLocal, endLocal);

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

        EventStatisticsKeySingleRequest three =
                EventStatisticsKeySingleRequest.builder().name("查看移动工作室").flag(ReportPropertyFlag.LABEL).build();
        Map<Integer, List<EventDTO>> threeMap =
                this.buildDTOBetween(siteId, three, startLocal, endLocal).parallelStream().collect(
                        Collectors.groupingBy(EventDTO::getStart_date));


        List<APlusSourceListDTO> result =
                durationDates.parallelStream().sorted(Comparator.reverseOrder()).map(date -> {
                    List<APlusSourceBaiduDTO> oneCollect = oneMap.get(date);
                    List<APlusSourceBaiduDTO> twoCollect = twoMap.get(date);
                    List<EventDTO> threeCollect = threeMap.get(date);

                    Long shareFromABOPV = oneCollect.parallelStream().filter(dto -> dto.getName().indexOf("-PV")!=-1)
                            .findFirst().orElse(APlusSourceBaiduDTO.builder().build()).getValue();
                    Long shareFromABOUV = oneCollect.parallelStream().filter(dto -> dto.getName().indexOf("-UV")!=-1)
                            .findFirst().orElse(APlusSourceBaiduDTO.builder().build()).getValue();
                    Long shareNotFromABOPV = twoCollect.parallelStream().filter(dto -> dto.getName().indexOf("-PV")!=-1)
                            .findFirst().orElse(APlusSourceBaiduDTO.builder().build()).getValue();
                    Long shareNotFromABOUV = twoCollect.parallelStream().filter(dto -> dto.getName().indexOf("-UV")!=-1)
                            .findFirst().orElse(APlusSourceBaiduDTO.builder().build()).getValue();

                    return APlusSourceListDTO.builder()
                                .date(date)
                                .shareFromABOPV(shareFromABOPV)
                                .shareFromABOUV(shareFromABOUV)
                                .shareNotFromABOPV(shareNotFromABOPV)
                                .shareNotFromABOUV(shareNotFromABOUV)
                                .aaWorkshopPV(threeCollect.get(0).getPv())
                                .aaWorkshopUV(threeCollect.get(0).getUv())
                                .build();
                }).collect(Collectors.toList());

        APlusSourceListDTO sum = APlusSourceListDTO.builder()
                .shareFromABOPV(result.parallelStream().collect(Collectors.summingLong(APlusSourceListDTO::getShareFromABOPV)))
                .shareFromABOUV(result.parallelStream().collect(Collectors.summingLong(APlusSourceListDTO::getShareFromABOUV)))
                .shareNotFromABOPV(result.parallelStream().collect(Collectors.summingLong(APlusSourceListDTO::getShareNotFromABOPV)))
                .shareNotFromABOUV(result.parallelStream().collect(Collectors.summingLong(APlusSourceListDTO::getShareNotFromABOUV)))
                .aaWorkshopPV(result.parallelStream().collect(Collectors.summingLong(APlusSourceListDTO::getAaWorkshopPV)))
                .aaWorkshopUV(result.parallelStream().collect(Collectors.summingLong(APlusSourceListDTO::getAaWorkshopUV)))
                .build();

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount()).add("sum", sum);
    }


    /**
     * 获取素材模板数据列表
     * @param start
     * @param end
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation("A+画册 - 素材统计 - 模板素材 - 列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("materialStatistics/template/list")
    public Response templateListData(@RequestParam String start,
                                     @RequestParam String end,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(required = false, defaultValue = "100") Integer pageSize){
        return aPlusService.templateListData(start, end, pageIndex, pageSize);
    }

    /**
     * 获取素材音乐数据列表
     * @param start
     * @param end
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation("A+画册 - 素材统计 - 音乐素材 - 列表")
    @ApiImplicitParams(value = {
            @ApiImplicitParam(name = "start", value = "起始时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束时间 yyyy-MM-dd", dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "当前页码", dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "一页行数", dataType = "int", paramType = "query")
    })
    @GetMapping("materialStatistics/music/list")
    public Response musicListData(@RequestParam String start,
                                     @RequestParam String end,
                                     @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                                     @RequestParam(required = false, defaultValue = "10") Integer pageSize){
        return aPlusService.musicListData(start, end, pageIndex, pageSize);
    }









    public Integer parseDateToInteger(String date){
        return Integer.valueOf(LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("yyyMMdd")).toString());
    }

    public List<EventDTO> buildDTOBetween(Integer siteId, EventStatisticsKeySingleRequest req,
                                          LocalDate start, LocalDate end){

        Set<Integer> durationDates = this.parseLocalDateToStringSet(start, end);

        return durationDates.parallelStream().map(date -> this.buildDTO(siteId, req, date))
                        .filter(o -> o.isPresent())
                        .map(o -> o.get())
                        .collect(Collectors.toList());
    }

    public Optional<EventDTO> buildDTO(Integer siteId, EventStatisticsKeySingleRequest req, Integer date){

        List<EventStatisticsKeySingleRequest> requestEvent = new ArrayList<>();
        requestEvent.add(req);

        EventStatisticsKeyRequest r = EventStatisticsKeyRequest.builder()
                .events(requestEvent)
                .start_date(date)
                .end_date(date)
                .site_id(siteId).build();

        Response resp = baiduService.key(r, 1, 100);
        JSONArray body = JSONArray.parseArray(JSONArray.toJSONString(resp.get("body")));

        return body.parallelStream()
                .map(object -> JSONObject.parseObject(JSONObject.toJSONString(object), EventDTO.class)).findFirst();
    }
}
