package org.codeworks.web.toolsdashboardbaidu.controller;

import com.baidu.statistics.dataapi.om.profile.GetDataCollectionRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataRequest;
import com.baidu.statistics.dataapi.om.profile.Site;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("statistics")
@RestController
public class StatisticsController implements StatisticsService{

    @Autowired
    StatisticsService statisticsService;

    @GetMapping("achieveBaiduSites")
    public List<Site> achieveBaiduSites() {
        return statisticsService.achieveBaiduSites();
    }

    @PostMapping("achieveBaiduData")
    public Map achieveBaiduData(@RequestBody GetDataRequest request) {
        return statisticsService.achieveBaiduData(request);
    }

    @PostMapping("achieveBaiduDatas")
    public List<Map> achieveBaiduDatas(@RequestBody GetDataCollectionRequest request) {
        return statisticsService.achieveBaiduDatas(request);
    }
}
