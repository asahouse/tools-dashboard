package org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics;

import lombok.Data;

/**
 * Created by benjaminkc on 16/12/9.
 * Modify At 17/11/29
 * Point: 新增传递参数,适应事件跟踪/自定义变量/外部链接/推广方式等报告更新使用
 */
@Data
public class GetDataRequestRequisite extends ApiRequest{

    //Core Requisite
    private String method;              //通常对应要查询的报告, 事件有(custom/event_track/a , custom/event_track/f 分别是列表,时序)
    private String metrics;             //自定义指标选择,多个指标用逗号分隔

    //Requisite
    private int site_id;
    private int start_date;
    private int end_date;
    private int start_date2;
    private int end_date2;
    private String gran;                //时间粒度(只支持有该参数的报告): day/hour/week/month
    private String order;               //指标排序,示例:visitor_count,desc
    private int start_index;            //获取数据偏移,用于分页;默认是 0
    private int max_results;            //单次获取数据条数,用于分页;默认是 20; 0 表示获取所有数据.
    private int offset;
}
