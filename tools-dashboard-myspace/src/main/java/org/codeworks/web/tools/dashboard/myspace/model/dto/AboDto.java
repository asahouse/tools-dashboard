package org.codeworks.web.tools.dashboard.myspace.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AboDto extends BaseDto {

    private static final long serialVersionUID = 5216238034668787655L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String nickname;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer count;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ada;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String openId;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getAda() {
        return ada;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
