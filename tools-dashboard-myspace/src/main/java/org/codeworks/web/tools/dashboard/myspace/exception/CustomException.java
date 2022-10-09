package org.codeworks.web.tools.dashboard.myspace.exception;

import java.util.ResourceBundle;

/**
 * 通用异常
 */
public class CustomException extends RuntimeException {

    static ResourceBundle rb;

    static{
        rb = ResourceBundle.getBundle("i18n/messages");
    }

    private int code;
    private String msg;

    private String url;

    public CustomException(){
        super(rb.getString("500"));
        this.code = 500;
        this.msg = this.getMessage();
    }

    public CustomException(int code){
        super(rb.getString(""+code));
        this.code = code;
        this.msg = this.getMessage();
    }

    public CustomException(int code,String msg){
        super();
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String getUrl() {
        return url;
    }
}
