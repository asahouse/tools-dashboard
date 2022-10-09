package org.codeworks.web.toolsdashboardbaidu.assist.statistics;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.statistics.dataapi.om.profile.GetDataCollectionRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataRequest;
import com.baidu.statistics.dataapi.om.profile.GetDataRequestRequisite;
import com.baidu.statistics.dataapi.om.profile.Site;
import lombok.extern.slf4j.Slf4j;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.service.StatisticsService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benjaminkc on 16/12/11.
 */
@Slf4j
@RunWith(SpringRunner.class)
@ActiveProfiles("local")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticsTest {

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void testSites(){
        List<Site> sites = new ArrayList<>();
        try {
            sites = statisticsService.achieveBaiduSites();
            log.info(JSONArray.toJSONString(sites));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(sites);
    }

    @Test
    public void testData(){
        Map data = new HashMap();
        try {
            GetDataRequest rq = new GetDataRequest();
            rq.setSite_id(10049961);
            rq.setStart_date(20171101);
            rq.setEnd_date(20171105);
//            rq.setStart_date2(20171101);
//            rq.setEnd_date2(20171105);
            rq.setMethod("trend/time/a");
            rq.setMetrics("pv_count,visitor_count");
            rq.setFlag("today");
            rq.setGran("day");
            rq.setClientDevice("all");

            data = statisticsService.achieveBaiduData(rq);
            log.info(JSONObject.toJSONString(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(data);
    }

    @Test
    public void testDatas(){
        List<Map> data = new ArrayList<>();
        try {
            GetDataRequestRequisite rr = new GetDataRequestRequisite();
            rr.setSite_id(10049961);
            rr.setStart_date(20171021);
            rr.setEnd_date(20171125);

            GetDataRequest rq = new GetDataRequest();
            rq.setMethod("overview/getTimeTrendRpt");
            rq.setMetrics("ip_count");

            GetDataRequest rq2 = new GetDataRequest();
            rq2.setMethod("overview/getTimeTrendRpt");
            rq2.setMetrics("pv_count");

            GetDataCollectionRequest rqCol = new GetDataCollectionRequest(rr,rq,rq2);

            data = statisticsService.achieveBaiduDatas(rqCol);
            log.info(JSONObject.toJSONString(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(data);
    }

}
