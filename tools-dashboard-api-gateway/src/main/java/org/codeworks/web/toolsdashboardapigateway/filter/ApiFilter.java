package org.codeworks.web.toolsdashboardapigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import lombok.extern.java.Log;
import org.codeworks.web.toolsdashboardapigateway.service.AuthorizationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Log
@Component
public class ApiFilter extends ZuulFilter {

    @Autowired
    private AuthorizationSerivce authorizationSerivce;

    @Override
    public Object run(){

        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        String requestPath = request.getRequestURI();
        String token = Optional.ofNullable(request.getHeader("token")).orElse("");

        if (requestPath.indexOf("/auth/")!=-1 ||
                requestPath.indexOf("/download/")!=-1) return null;

        if(authorizationSerivce.haslogin(token)) {// 如果请求的参数不为空
            ctx.setSendZuulResponse(true);// 对该请求进行路由
            ctx.setResponseStatusCode(200);
            ctx.set("isSuccess", true);// 设值，让下一个Filter看到上一个Filter的状态
            return null;
        }else{
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(401);// 返回错误码
            ctx.setResponseBody("{\"result\":\"token is fail!\"}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public String filterType() {
        return "pre";// 前置过滤器
    }
}
