package org.codeworks.web.tools.dashboard.myspace.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class MusicDataDto extends BaseDto {

    private static final long serialVersionUID = 407196379706064298L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer musicCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer useCount;

    public Integer getMusicCode() {
        return musicCode;
    }

    public void setMusicCode(Integer musicCode) {
        this.musicCode = musicCode;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }
}
