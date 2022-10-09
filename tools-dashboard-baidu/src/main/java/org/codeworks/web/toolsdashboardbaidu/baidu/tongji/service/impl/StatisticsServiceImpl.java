package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.service.impl;

import com.baidu.statistics.dataapi.om.profile.*;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.ErrorCodes;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.StatisticsException;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.service.StatisticsService;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic.BaiduStatisticsApi;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic.BaiduStatisticsCacheApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by benjaminkc on 16/12/11.
 */
@Primary
@Service
public class StatisticsServiceImpl implements StatisticsService {
    @Autowired
    //private BaiduStatisticsApi baiduStatisticsApi;
    private BaiduStatisticsCacheApi baiduStatisticsApi;

    @Override
    public List<Site> achieveBaiduSites(){
        GetSiteListResponse rq = baiduStatisticsApi.requestSiteList();
        if (!Optional.ofNullable(rq).isPresent()) {
            throw new StatisticsException(ErrorCodes.baidu_statistics_sites_empty);
        }

        return rq.getList();
    }

    @Override
    public Map achieveBaiduData(GetDataRequest request){
        GetDataResponse rq = baiduStatisticsApi.requestData(request);
        if (!Optional.ofNullable(rq).isPresent()) {
            throw new StatisticsException(ErrorCodes.baidu_statistics_data_wrong);
        }
        return rq.getResult();
    }

    @Override
    public List<Map> achieveBaiduDatas(GetDataCollectionRequest request){
        List<GetDataResponse> rqs;
        try {
            rqs = baiduStatisticsApi.requestCollectionData(request);
        } catch (RuntimeException e) {
            throw new StatisticsException(ErrorCodes.baidu_statistics_data_collection_error,
                    new Object[]{"achieveBaiduDatas", e.getLocalizedMessage()});
        }

        if (!Optional.ofNullable(rqs).isPresent()
                || rqs.isEmpty()) {
            throw new StatisticsException(ErrorCodes.baidu_statistics_data_collection_wrong);
        }

        return rqs.stream().map(GetDataResponse::getResult).collect(Collectors.toList());
    }
}
