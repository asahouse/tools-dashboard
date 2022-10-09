package org.codeworks.web.tools.dashboard.myspace.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Web工具
 */
public class WebUtil {

    /**
     * 获取客户机的直实IP
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request){
        if(request==null){
            return null;
        }
        String remoteAddr = request.getRemoteAddr();
        String forwarded = request.getHeader("X-Forwarded-For");
        String realIp = request.getHeader("X-Real-IP");
        String ip = null;
        if (realIp == null) {
            if (forwarded == null) {
                ip = remoteAddr;
            } else {
                ip = remoteAddr + "/" + forwarded.split(",")[0];
            }
        } else {
            if (realIp.equals(forwarded)) {
                ip = realIp;
            } else {
                if(forwarded != null){
                    forwarded = forwarded.split(",")[0];
                }
                ip = realIp + "/" + forwarded;
            }
        }
        return ip;
    }

}
