package org.codeworks.web.tools.dashboard.myspace.utils;

import java.util.ResourceBundle;

public class MessageUtils {

    static ResourceBundle rb;

    static{
        rb = ResourceBundle.getBundle("i18n/messages");
    }

    public static String getMessage(String key){
        return rb.getString(key);
    }

}
