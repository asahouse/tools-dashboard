package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception;

/**
 * Created by benjaminkc on 17/5/23.
 */
public enum ErrorCodes {
    //Baidu Statistics
    baidu_statistics_init_error("baidu.statistics.api.init.error"),
    baidu_statistics_request_error("baidu.statistics.api.request.error"),
    baidu_statistics_prelogin_error("baidu.statistics.prelogin.error"),
    baidu_statistics_prelogin_auth("baidu.statistics.prelogin.auth"),
    baidu_statistics_prelogin_format("baidu.statistics.prelogin.format"),
    baidu_statistics_dologin_error("baidu.statistics.dologin.error"),
    baidu_statistics_dologin_format("baidu.statistics.dologin.format"),
    baidu_statistics_dologin_return_error("baidu.statistics.dologin.return.error"),
    baidu_statistics_dologout_error("baidu.statistics.dologin.error"),
    baidu_statistics_dologout_format("baidu.statistics.dologin.format"),
    baidu_statistics_sites_empty("baidu.statistics.sites.empty"),
    baidu_statistics_sites_error("baidu.statistics.sites.error"),
    baidu_statistics_data_error("baidu.statistics.data.error"),
    baidu_statistics_data_wrong("baidu.statistics.data.wrong"),
    baidu_statistics_data_collection_error("baidu.statistics.data.collection.error"),
    baidu_statistics_data_collection_wrong("baidu.statistics.data.collection.wrong")
    ;


    private String code;

    ErrorCodes(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
