package org.codeworks.web.toolsdashboardwebfacade.dto.baidu.statistics;

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
