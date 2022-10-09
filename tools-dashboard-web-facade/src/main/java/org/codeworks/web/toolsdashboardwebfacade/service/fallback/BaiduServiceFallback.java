package org.codeworks.web.toolsdashboardwebfacade.service.fallback;

import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardwebfacade.dto.Response;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.EventStatisticsKeyRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.PageStatisticsTotalRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.PageStatisticsVisitRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataCollectionRequest;
import org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics.GetDataRequest;
import org.codeworks.web.toolsdashboardwebfacade.service.BaiduService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务降级处理类
 */
@Slf4j
//@Component
public class BaiduServiceFallback implements BaiduService{

    @Override
    public Response keyDetail(EventStatisticsKeyRequest request, Integer pageIndex, Integer pageSize) {
        return Response.error("Error fallback!");
    }

    @Override
    public Response key(EventStatisticsKeyRequest request, Integer pageIndex, Integer pageSize) {
        return Response.error("Error fallback!");
    }

    @Override
    public Response total(PageStatisticsTotalRequest request, Integer pageIndex, Integer pageSize) {
        return Response.error("Error fallback!");
    }

    @Override
    public Response visit(PageStatisticsVisitRequest request, Integer pageIndex, Integer pageSize) {
        return Response.error("Error fallback!");
    }

    @Override
    public Map achieveBaiduData(GetDataRequest request) {
        return new HashMap();
    }

    @Override
    public List<Map> achieveBaiduDatas(GetDataCollectionRequest request) {
        return new ArrayList<>();
    }
}
