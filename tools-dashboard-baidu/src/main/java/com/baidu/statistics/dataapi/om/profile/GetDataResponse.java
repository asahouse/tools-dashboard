package com.baidu.statistics.dataapi.om.profile;

import com.baidu.statistics.dataapi.core.ApiResponse;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by benjaminkc on 16/12/9.
 */
@Data
public class GetDataResponse extends ApiResponse {
    private Map result = new HashMap();
}
