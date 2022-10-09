package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception;

/**
 * Created by Luis on 2016/8/25.
 */
public interface Errors {

    ErrorCodes getErrorCodes();

    boolean getIsLog();

    Object[] getArguments();
}
