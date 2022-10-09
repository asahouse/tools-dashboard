package com.baidu.statistics.dataapi.om.profile;

import com.baidu.statistics.dataapi.core.ApiResponse;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by benjaminkc on 16/12/9.
 */
@Data
public class GetSiteListResponse extends ApiResponse {
    private List<Site> list = new ArrayList<>();
}
