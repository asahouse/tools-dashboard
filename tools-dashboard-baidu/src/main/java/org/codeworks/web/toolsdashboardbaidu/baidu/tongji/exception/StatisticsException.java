package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception;

/**
 * Created by benjaminkc on 16/12/11.
 */
public class StatisticsException extends RuntimeException implements Errors{

    private ErrorCodes errorCodes;

    private boolean isLog = false;

    private Object[] arguments;

    public StatisticsException(ErrorCodes errorCodes) {
        super();
        this.errorCodes = errorCodes;
    }

    public StatisticsException(ErrorCodes errorCodes, boolean isLog) {
        super();
        this.errorCodes = errorCodes;
        this.isLog = isLog;
    }
    public StatisticsException(ErrorCodes errorCodes, Object[] arguments) {
        super();
        this.errorCodes = errorCodes;
        this.arguments = arguments;
    }

    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    public boolean getIsLog() {
        return isLog;
    }

    public Object[] getArguments() {
        return arguments;
    }

}
