package org.codeworks.web.tools.dashboard.myspace.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class TemplateDataDto extends BaseDto {

    private static final long serialVersionUID = -5913944649050997195L;

    private Integer templateCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer galleryCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer creatorCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer likeCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer useCount;

    public Integer getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(Integer templateCode) {
        this.templateCode = templateCode;
    }

    public Integer getGalleryCount() {
        return galleryCount;
    }

    public void setGalleryCount(Integer galleryCount) {
        this.galleryCount = galleryCount;
    }

    public Integer getCreatorCount() {
        return creatorCount;
    }

    public void setCreatorCount(Integer creatorCount) {
        this.creatorCount = creatorCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getUseCount() {
        return useCount;
    }

    public void setUseCount(Integer useCount) {
        this.useCount = useCount;
    }
}
