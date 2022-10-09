package org.codeworks.web.toolsdashboardbaidu.baidu.tongji.statistic.exception;

import lombok.Data;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.ErrorCodes;
import org.codeworks.web.toolsdashboardbaidu.baidu.tongji.exception.Errors;

/**
 * Created by benjaminkc on 16/12/11.
 */
@Data
public class StatisticsBaiduException extends RuntimeException implements Errors {

    private ErrorCodes errorCodes;

    private boolean isLog = false;

    private Object[] arguments;

    public StatisticsBaiduException(ErrorCodes errorCodes) {
        super();
        this.errorCodes = errorCodes;
    }

    public StatisticsBaiduException(ErrorCodes errorCodes, boolean isLog) {
        super();
        this.errorCodes = errorCodes;
        this.isLog = isLog;
    }
    public StatisticsBaiduException(ErrorCodes errorCodes, Object[] arguments) {
        super();
        this.errorCodes = errorCodes;
        this.arguments = arguments;
    }

    @Override
    public ErrorCodes getErrorCodes() {
        return errorCodes;
    }

    @Override
    public boolean getIsLog() {
        return isLog;
    }

    @Override
    public Object[] getArguments() {
        return arguments;
    }

}
