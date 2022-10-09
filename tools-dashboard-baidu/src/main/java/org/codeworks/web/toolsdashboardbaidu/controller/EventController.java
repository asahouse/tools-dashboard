package org.codeworks.web.toolsdashboardbaidu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.statistics.dataapi.om.profile.GetDataRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.service.StatisticsService;
import org.codeworks.web.toolsdashboardbaidu.dto.*;
import org.codeworks.web.toolsdashboardbaidu.enumtype.ReportPropertyFlag;
import org.codeworks.web.toolsdashboardbaidu.utils.ArrayPage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@RequestMapping("event")
@RestController
public class EventController {

    @Autowired
    StatisticsService statisticsService;

    @PostMapping("keyDetail")
    public Response keyDetail(@RequestBody EventStatisticsKeyRequest request,
                        @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        Map<ReportPropertyFlag, List<EventStatisticsKeySingleRequest>> flagMap =
                request.getEvents().parallelStream()
                        .collect(Collectors.groupingBy(EventStatisticsKeySingleRequest::getFlag));

        LocalDate start = LocalDate.parse(String.valueOf(request.getStart_date()),DateTimeFormatter.ofPattern("yyyyMMdd"));
        LocalDate end = LocalDate.parse(String.valueOf(request.getEnd_date()),DateTimeFormatter.ofPattern("yyyyMMdd"));

        if (start.isAfter(end)) return Response.error("End Date CAN NOT After Start Date!!");

        Long duratingDays = ChronoUnit.DAYS.between(start, end)+1;

        List<LocalDate> durationDates = LongStream.range(0, duratingDays.intValue())
                .mapToObj(i -> i>0 ? start.plusDays(i) : start.minusDays(i)).collect(Collectors.toList());


        List<EventStatisticsCollectResponse> result = durationDates.parallelStream().flatMap(d -> {
            Integer rqDate = Integer.valueOf(d.format(DateTimeFormatter.ofPattern("yyyyMMdd")).toString());

            return flagMap.entrySet().parallelStream()
                            .flatMap(flag -> {
                                EventStatisticsKeyRequest r = new EventStatisticsKeyRequest();
                                BeanUtils.copyProperties(request, r);
                                r.setStart_date(rqDate);
                                r.setEnd_date(rqDate);
                                return this.getStream(r, flag);
                            })
                            .filter(resp -> request.getEvents().parallelStream()
                                    .filter(req -> req.getFlag().equals(resp.getFlag())
                                            && (req.getName().equals(resp.getName()) || resp.getName().indexOf(req.getName())!=-1) )
                                    .findAny().isPresent());

        }).collect(Collectors.groupingBy(EventStatisticsKeySingleResponse::getStart_date))
                .entrySet().parallelStream().map(single -> EventStatisticsCollectResponse.builder()
                    .date(single.getKey())
                    .collect(Arrays.asList(single.getValue().toArray(new EventStatisticsKeySingleResponse[]{})))
                    .build())
                .sorted(Comparator.comparingInt(EventStatisticsCollectResponse::getDate))
                .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

    @PostMapping("key")
    public Response key(@RequestBody EventStatisticsKeyRequest request,
                        @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                        @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        Map<ReportPropertyFlag, List<EventStatisticsKeySingleRequest>> flagMap =
            request.getEvents().parallelStream()
                    .collect(Collectors.groupingBy(EventStatisticsKeySingleRequest::getFlag));

        List<EventStatisticsKeySingleResponse> result =
            flagMap.entrySet().parallelStream()
                    .flatMap(flag -> this.getStream(request, flag))
                    .filter(resp -> request.getEvents().parallelStream()
                            .filter(req -> req.getFlag().equals(resp.getFlag())
                                    && (req.getName().equals(resp.getName()) || resp.getName().indexOf(req.getName())!=-1) )
                            .findAny().isPresent())
                    .collect(Collectors.toList());

        ArrayPage page = new ArrayPage(result.toArray(), pageIndex, pageSize);
        return Response.ok("body", page.getDisplayResult()).add("total",page.getTotalCount());
    }

    private Stream<? extends EventStatisticsKeySingleResponse> getStream(
            EventStatisticsKeyRequest request,
            Map.Entry<ReportPropertyFlag, List<EventStatisticsKeySingleRequest>> flag) {


        GetDataRequest rq = new GetDataRequest();
            rq.setMethod("custom/event_track/a");//事件跟踪报告
            rq.setSite_id(request.getSite_id());
            rq.setStart_date(request.getStart_date());
            rq.setEnd_date(request.getEnd_date());
            rq.setMetrics(request.getMetrics());
            rq.setFlag(flag.getKey().toValue());

        Map single = statisticsService.achieveBaiduData(rq);
        JSONArray list = JSONObject.parseObject(JSONObject.toJSONString(single))
                .getJSONArray("items");

        JSONArray titles = (JSONArray) list.get(0);
        JSONArray values = (JSONArray) list.get(1);

        List<EventStatisticsKeySingleResponse> coll = new ArrayList<>();

        if (titles.size() != values.size()) return Stream.empty();
        else {
            //由于百度输出格式固定,有值的话,层级不变
            IntStream.range(0, titles.size()).forEach(i -> {
                StringBuffer sb = new StringBuffer();
                if (flag.getKey().equals(ReportPropertyFlag.ALL)) {
                    String a = ((JSONObject) ((JSONArray) titles.get(i)).get(0)).getString("a");
                    String c = ((JSONObject) ((JSONArray) titles.get(i)).get(0)).getString("c");
                    String l = ((JSONObject) ((JSONArray) titles.get(i)).get(0)).getString("l");
                    sb.append(a).append(",").append(c).append(",").append(l);
                }else {
                    String label = ((JSONObject) ((JSONArray) titles.get(i)).get(0)).getString("label");
                    sb.append(label);
                }
                //时间跨度超过当前日期后会出现 "--"
                String pvString = ((JSONArray) values.get(i)).getString(0);
                String uvString = ((JSONArray) values.get(i)).getString(1);
                Integer pv = NumberUtils.isDigits(pvString) ? Integer.valueOf(pvString) : 0;
                Integer uv = NumberUtils.isDigits(uvString) ? Integer.valueOf(uvString) : 0;

                EventStatisticsKeySingleResponse resp = EventStatisticsKeySingleResponse.builder()
                        .name(sb.toString()).pv(pv).uv(uv).flag(flag.getKey())
                        .start_date(request.getStart_date()).end_date(request.getEnd_date()).build();
                coll.add(resp);
            });
            return coll.parallelStream();
        }
    }

}
