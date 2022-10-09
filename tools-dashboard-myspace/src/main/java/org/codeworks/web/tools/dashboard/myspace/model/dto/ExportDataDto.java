package org.codeworks.web.tools.dashboard.myspace.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class ExportDataDto extends GalleryDataDto {

    private static final long serialVersionUID = -5125043393355930351L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TemplateDataDto> templateData;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<MusicDataDto> musicData;

    public List<TemplateDataDto> getTemplateData() {
        return templateData;
    }

    public void setTemplateData(List<TemplateDataDto> templateData) {
        this.templateData = templateData;
    }

    public List<MusicDataDto> getMusicData() {
        return musicData;
    }

    public void setMusicData(List<MusicDataDto> musicData) {
        this.musicData = musicData;
    }
}
