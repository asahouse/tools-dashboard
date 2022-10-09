package org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics;

import lombok.Data;

/**
 * Created by benjaminkc on 16/12/9.
 * Modify At 17/11/29
 *
 * 注:

 1. method 参数,通常对应要查询的报告,与网站页面所发送请求的 method 参数一致,如要获取
 趋势分析报告的数据,所用到的方法为“trend/time/a”。

 2. metrics 参数,是所要获取的指标,根据不同的报告填写相关指标,与网站页面所发送请求的
 indicators 参数一致,如要获取浏览量(PV)、访客数(UV)、新访客数,则所填指标为
 “pv_count,visitor_count,new_visitor_count”。
 详见下面“关键参数与报告的对应”的描述。

 */
@Data
public class GetDataRequest extends GetDataRequestRequisite{

    //option
    private int target;                 //转化目标: -1:全部页面目标 -2:全部事件目标 -3:时长目标 -4:访问页数目标
    private String source;              //来源过滤: through:直接访问 search,0:搜索引擎全部 link:外部链接
    private String clientDevice;        //设备过滤: pc:计算机 mobile:移动设备
    private String area;                //地域过滤: china:全国 province,1:省市自治区北京 province,2:省市自治区上海 province,3:省市自治区天津 other:其他
    private String visitor;             //访客过滤: new:新访客 old:老访客

    private String flag;                //特殊条件参数 使用报告 -> 推广方式(flag:product) / 事件跟踪(flag:category(类别)/action(操作)/label(标签)) / 自定义变量(flag:index1(访客类型1)/index2(访客类型2)/index3(访客类型3)/index4(访客类型4)/index5(访客类型5))
    private String viewType;            //特殊条件参数 使用报告 -> 全部来源(viewType:type(按来源类型)/site(按来源网站)) / 外部链接(viewType:domain(按域名)/url(按URL))
    private String domainType;          //特殊条件参数 使用报告 -> 外部链接(domainType:1(社会化媒体)/2(导航网站)/4(电子邮箱))
    private String searchWord;          //官方网页使用参数 使用报告-> 受访页面/入口页面(链接内的字符)
    private String indicators;          //官方网页使用参数 使用报告-> 等同metrics, 受访页面/入口页面(indicators:pv_count,visitor_count,outward_count,exit_count,average_stay_time)
}
