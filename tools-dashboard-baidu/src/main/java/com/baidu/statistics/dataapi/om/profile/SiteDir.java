package com.baidu.statistics.dataapi.om.profile;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by benjaminkc on 16/12/9.
 */
@Data
public class SiteDir implements Serializable {
    private String name;
    private String create_time;
    private int sub_dir_id;
}
