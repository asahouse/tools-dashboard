package org.codeworks.web.tools.dashboard.myspace.web;

import java.util.HashMap;

/**
 * 通用返回
 * @param
 */
public class Response extends HashMap {

    private static final long serialVersionUID = -5148239577980920510L;

    private Response(){}

    public static final Integer SUCCESS = 200;

    public static final Integer ERROR = 500;

    public static Response ok(Object data){
        Response response = new Response();
        response.put("code",SUCCESS);
        response.put("message","success.");
        if(data!=null)
            response.put("body",data);
        return response;
    }

    public static Response ok(){
        return ok(null);
    }

    public static Response error(int code,String msg,String url){
        Response response = new Response();
        response.put("code",code);
        response.put("message",msg);
        if(url!=null && !"".equals(url))
            response.put("url",url);
        return response;
    }

    public Response add(String key,Object value){
        this.put(key,value);
        return this;
    }

}
