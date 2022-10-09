package com.baidu.statistics.dataapi.om.profile;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by benjaminkc on 16/12/9.
 */
@Data
public class ReportData implements Serializable{
    private String metrics;
    private Integer total;
    private Integer offset;
    private List<Object> sum;
    private List<Object> pageSum;
    private String analyticsResult;
    private List<Object> items;
    private List<String> timeSpan;
    private List<String> fields;
}
