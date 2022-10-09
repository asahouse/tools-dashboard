package org.codeworks.web.toolsdashboardbaidu.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.statistics.dataapi.om.profile.GetDataRequest;
import org.apache.commons.lang3.math.NumberUtils;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.service.StatisticsService;
import org.codeworks.web.toolsdashboardbaidu.dto.PageStatisticsTotalRequest;
import org.codeworks.web.toolsdashboardbaidu.dto.PageStatisticsTotalSingleResponse;
import org.codeworks.web.toolsdashboardbaidu.dto.PageStatisticsVisitRequest;
import org.codeworks.web.toolsdashboardbaidu.dto.PageStatisticsVisitSingleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequestMapping("page")
@RestController
public class PageController {

    @Autowired
    StatisticsService statisticsService;

    @PostMapping("total")
    public Response total(@RequestBody PageStatisticsTotalRequest request,
                          @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        GetDataRequest rq = new GetDataRequest();
            rq.setSite_id(request.getSite_id());
            rq.setStart_date(request.getStart_date());
            rq.setEnd_date(request.getEnd_date());
            rq.setMethod("trend/time/a");
            rq.setMetrics(request.getMetrics());
            rq.setGran(request.getGran().toValue());
            rq.setStart_index((pageIndex>0?pageIndex-1:pageIndex) * pageSize);
            rq.setMax_results(pageSize);
            rq.setOrder("simple_date_title,asc");

        Map single = statisticsService.achieveBaiduData(rq);
        JSONArray list = JSONObject.parseObject(JSONObject.toJSONString(single))
                .getJSONArray("items");

        JSONArray titles = (JSONArray) list.get(0);
        JSONArray values = (JSONArray) list.get(1);

        List<PageStatisticsTotalSingleResponse> result = IntStream.range(0, titles.size()).mapToObj(i -> {
            String dateTitle = ((JSONArray) titles.get(i)).get(0).toString();
            String pvString = ((JSONArray) values.get(i)).getString(0);
            String uvString = ((JSONArray) values.get(i)).getString(1);
            Integer pv = NumberUtils.isDigits(pvString) ? Integer.valueOf(pvString) : 0;
            Integer uv = NumberUtils.isDigits(uvString) ? Integer.valueOf(uvString) : 0;
            return PageStatisticsTotalSingleResponse.builder()
                    .dateTitle(dateTitle).pv(pv).uv(uv).build();
        }).collect(Collectors.toList());

        return Response.ok("body",result)
                .add("total", JSONObject.parseObject(JSONObject.toJSONString(single)).getIntValue("total"));
    }

    @PostMapping("visit")
    public Response visit(@RequestBody PageStatisticsVisitRequest request,
                          @RequestParam(required = false, defaultValue = "1") Integer pageIndex,
                          @RequestParam(required = false, defaultValue = "10") Integer pageSize){

        GetDataRequest rq = new GetDataRequest();
            rq.setSite_id(request.getSite_id());
            rq.setStart_date(request.getStart_date());
            rq.setEnd_date(request.getEnd_date());
            rq.setMethod("visit/toppage/a");
            rq.setMetrics(request.getMetrics());
            rq.setStart_index((pageIndex>0?pageIndex-1:pageIndex) * pageSize);
            rq.setMax_results(pageSize);
            rq.setOrder("pv_count,desc");
            rq.setSearchWord(request.getSearchWord());

        Map single = statisticsService.achieveBaiduData(rq);
        JSONArray list = JSONObject.parseObject(JSONObject.toJSONString(single))
                .getJSONArray("items");

        JSONArray titles = (JSONArray) list.get(0);
        JSONArray values = (JSONArray) list.get(1);

        JSONArray sum = (JSONArray) JSONObject.parseObject(JSONObject.toJSONString(single))
                .getJSONArray("sum").get(0);//对比日期关系, 数组第一个为默认日期对总和
        String sumPvString = sum.getString(0);
        String sumUvString = sum.getString(1);
        Integer sumPv = NumberUtils.isDigits(sumPvString) ? Integer.valueOf(sumPvString) : 0;
        Integer sumUv = NumberUtils.isDigits(sumUvString) ? Integer.valueOf(sumUvString) : 0;

        List<PageStatisticsVisitSingleResponse> result = IntStream.range(0, titles.size()).mapToObj(i -> {
            String dateTitle = ((JSONObject)((JSONArray) titles.get(i)).get(0)).getString("name").toString();
            String pvString = ((JSONArray) values.get(i)).getString(0);
            String uvString = ((JSONArray) values.get(i)).getString(1);
            Integer pv = NumberUtils.isDigits(pvString) ? Integer.valueOf(pvString) : 0;
            Integer uv = NumberUtils.isDigits(uvString) ? Integer.valueOf(uvString) : 0;

            return PageStatisticsVisitSingleResponse.builder()
                    .dateTitle(dateTitle).pv(pv).uv(uv).build();
        }).collect(Collectors.toList());

        return Response.ok("body",result)
                .add("total", JSONObject.parseObject(JSONObject.toJSONString(single)).getIntValue("total"))
                .add("sumPv", sumPv)
                .add("sumUv", sumUv);
    }

}
