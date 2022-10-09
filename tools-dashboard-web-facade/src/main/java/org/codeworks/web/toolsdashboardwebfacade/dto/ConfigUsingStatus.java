package org.codeworks.web.toolsdashboardwebfacade.dto;

/**
 * 用于配置模板/邀请函等配置启用状态
 */
public enum ConfigUsingStatus {
    ACTIVATION("activation"),INACTIVATION("inactivation");


    private String value;
    ConfigUsingStatus(String value) {
        this.value = value;
    }

    public static ConfigUsingStatus fromValue(String value){
        if (value != null) {
            for (ConfigUsingStatus type : values()) {
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
