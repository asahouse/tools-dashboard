package com.baidu.statistics.dataapi.svc;

import com.baidu.statistics.common.StatisticConstant;
import com.baidu.statistics.dataapi.core.*;
import com.baidu.statistics.dataapi.om.profile.*;
import com.baidu.statistics.exception.StaticsException;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by benjaminkc on 16/12/9.
 * Modify At 17/11/29
 */

@Slf4j
public class Report extends Base {

	public Report(Integer ucid, String st) {
		super(ucid, st);
	}

	public TongjiResponse<GetSiteListResponse> getSiteList() throws StaticsException {
		DataApiConnection conn = new DataApiConnection(ucid);
		AuthHeader header = new AuthHeader(st);
		GetSiteListRequest body = new GetSiteListRequest();
		TongjiRequest<GetSiteListRequest> holmesRq = new TongjiRequest<>(header, body, StatisticConstant.TONGJI_API_GETSITELIST);
		TongjiResponse<GetSiteListResponse> holmesRs = conn.post(holmesRq, GetSiteListResponse.class);
		return holmesRs;
	}

	public TongjiResponse<GetDataResponse> getData(GetDataRequest rq) throws StaticsException {
		DataApiConnection conn = new DataApiConnection(ucid);
		AuthHeader header = new AuthHeader(st);
		TongjiRequest<GetDataRequest> tongjiRq = new TongjiRequest<>(header, rq, StatisticConstant.TONGJI_API_GETDATA);
		TongjiResponse<GetDataResponse> rs = conn.post(tongjiRq, GetDataResponse.class);
		return rs;
	}

	public List<TongjiResponse<GetDataResponse>> getCollectionData(GetDataCollectionRequest rqCol) throws StaticsException {

		DataApiConnection conn = new DataApiConnection(ucid);
		AuthHeader header = new AuthHeader(st);

		List<TongjiResponse<GetDataResponse>> resResutl = new ArrayList<>();
		Optional.ofNullable(rqCol.getRequests()).ifPresent(rqs -> {
			rqs.forEach(rq ->{
				TongjiRequest<GetDataRequest> tongjiRq = new TongjiRequest<>(header, rq, StatisticConstant.TONGJI_API_GETDATA);
				TongjiResponse<GetDataResponse> rs = null;
				try{
					rs = conn.post(tongjiRq, GetDataResponse.class);
				}catch (StaticsException ex){log.error(ex.getMessage());}

				if (rs!=null) resResutl.add(rs);
			});
		});

		return resResutl;
	}
}
