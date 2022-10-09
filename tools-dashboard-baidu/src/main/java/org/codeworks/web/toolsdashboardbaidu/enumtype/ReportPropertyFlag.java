package org.codeworks.web.toolsdashboardbaidu.enumtype;

/**
 * 用于报告参数flag的类型限制
 *
 * ^ 事件跟踪/自定义报告/推广方式
 */
public enum ReportPropertyFlag {
    //事件跟踪(flag:category(类别)/action(操作)/label(标签))
    ALL("all"),LABEL("label"),ACTION("action"),CATEGORY("category"),
    //自定义变量(flag:index1(访客类型1)/index2(访客类型2)/index3(访客类型3)/index4(访客类型4)/index5(访客类型5))
    INDEX1("index1"),INDEX2("index2"),INDEX3("index3"),INDEX4("index4"),INDEX5("index5"),
    //推广方式
    PRODUCT("product"),
    //趋势分析
    TODAY("today")
    ;


    private String value;
    ReportPropertyFlag(String value) {
        this.value = value;
    }

    public static ReportPropertyFlag fromValue(String value){
        if (value != null) {
            for (ReportPropertyFlag type : values()) {
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
