package org.codeworks.web.toolsdashboardwebfacade.service;

import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.*;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataCollectionRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataRequest;
import org.codeworks.web.toolsdashboardwebfacade.service.fallback.BaiduServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "TD-BAIDU", fallback = BaiduServiceFallback.class)
public interface BaiduService {

    @RequestMapping(value = "event/keyDetail", method = RequestMethod.POST)
    Response keyDetail(@RequestBody EventStatisticsKeyRequest request,
                       @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                               Integer pageIndex,
                       @RequestParam(required = false, defaultValue = "10", value="pageSize")
                               Integer pageSize);

    @RequestMapping(value = "event/key", method = RequestMethod.POST)
    Response key(@RequestBody EventStatisticsKeyRequest request,
                 @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                         Integer pageIndex,
                 @RequestParam(required = false, defaultValue = "10", value="pageSize")
                         Integer pageSize);

    @RequestMapping(value = "page/total", method = RequestMethod.POST)
    Response total(@RequestBody PageStatisticsTotalRequest request,
                   @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                           Integer pageIndex,
                   @RequestParam(required = false, defaultValue = "10", value="pageSize")
                           Integer pageSize);

    @RequestMapping(value = "page/visit", method = RequestMethod.POST)
    Response visit(@RequestBody PageStatisticsVisitRequest request,
                   @RequestParam(required = false, defaultValue = "1", value="pageIndex")
                           Integer pageIndex,
                   @RequestParam(required = false, defaultValue = "10", value="pageSize")
                           Integer pageSize);


    @PostMapping("statistics/achieveBaiduData")
    Map achieveBaiduData(@RequestBody GetDataRequest request);

    @PostMapping("statistics/achieveBaiduDatas")
    List<Map> achieveBaiduDatas(@RequestBody GetDataCollectionRequest request);
}
