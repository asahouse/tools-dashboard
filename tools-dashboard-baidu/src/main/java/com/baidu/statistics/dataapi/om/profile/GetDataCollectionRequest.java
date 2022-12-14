package com.baidu.statistics.dataapi.om.profile;


import com.baidu.statistics.dataapi.core.ApiRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by benjaminkc on 16/12/9.
 */
@Data
@NoArgsConstructor
public class GetDataCollectionRequest extends ApiRequest{

    GetDataRequestRequisite requisite;

    List<GetDataRequest> requests;

    public GetDataCollectionRequest(GetDataRequestRequisite requisite, GetDataRequest... requests){
        this(requisite, Arrays.asList(requests));
    }

    public GetDataCollectionRequest(GetDataRequestRequisite requisite, List<GetDataRequest> requests){
        if (!Optional.ofNullable(requisite).isPresent() ||
                !Optional.ofNullable(requests).isPresent()) return;

        this.requisite = requisite;
        this.requests = requests;

        this.pre();
    }


    private void pre(){

        Optional.ofNullable(requisite).ifPresent(re -> this.requests.stream().forEach(rq ->{
            if (StringUtils.isNotBlank(rq.getMethod()))
                BeanUtils.copyProperties(re, rq, "method", "metrics", "target", "source", "clientDevice", "area", "visitor");
            else
                BeanUtils.copyProperties(re, rq, "metrics", "target", "source", "clientDevice", "area", "visitor");
        }));
    }

    public List<GetDataRequest> getRequests() {
        this.pre();

        return requests;
    }

}
