package org.codeworks.web.tools.dashboard.myspace.exception;

import org.apache.log4j.Logger;
import org.codeworks.web.tools.dashboard.myspace.web.Response;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    Logger log = Logger.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Response errorHandler(HttpServletRequest req, Exception e) throws Exception {
        CustomException ce = new CustomException(500,e.getMessage());
        if(e instanceof  CustomException){
            ce = (CustomException)e;
        }
        log.error(e.getMessage(),e);
        e.printStackTrace();
        return Response.error(ce.getCode(),ce.getMsg(),req.getRequestURI().toString());
    }

}
