package org.codeworks.web.toolsdashboardbaidu.enumtype;

/**
 * 用于报告参数gran的类型限制
 */
public enum ReportPropertyGran {
    //趋势分析
    DAY("day"),HOUR("hour"),WEEK("week"),MONTH("month")
    ;


    private String value;
    ReportPropertyGran(String value) {
        this.value = value;
    }

    public static ReportPropertyGran fromValue(String value){
        if (value != null) {
            for (ReportPropertyGran type : values()) {
                if (type.value.equals(value)) {
                    return type;
                }
            }
        }
        return null;
    }

    public String toValue() {
        return value;
    }
}
