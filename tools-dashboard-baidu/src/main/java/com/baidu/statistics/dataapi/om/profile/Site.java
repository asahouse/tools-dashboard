package com.baidu.statistics.dataapi.om.profile;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by benjaminkc on 16/12/9.
 */
@Data
public class Site implements Serializable{
    private int status;
    private String create_time;
    private String domain;
    private int site_id;

    private List<SiteDir> sub_dir_list;
}
