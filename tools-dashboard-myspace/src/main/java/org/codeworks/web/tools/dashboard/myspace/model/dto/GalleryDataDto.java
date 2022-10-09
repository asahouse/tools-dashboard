package org.codeworks.web.tools.dashboard.myspace.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;

public class GalleryDataDto extends BaseDto {

    private static final long serialVersionUID = -8628926006202347260L;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer date;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer creatorCountWithQuantity;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer creatorCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer galleryCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private BigDecimal avgPage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer likeCount;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private AboDto topCreator;

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public Integer getCreatorCountWithQuantity() {
        return creatorCountWithQuantity;
    }

    public void setCreatorCountWithQuantity(Integer creatorCountWithQuantity) {
        this.creatorCountWithQuantity = creatorCountWithQuantity;
    }

    public Integer getCreatorCount() {
        return creatorCount;
    }

    public void setCreatorCount(Integer creatorCount) {
        this.creatorCount = creatorCount;
    }

    public Integer getGalleryCount() {
        return galleryCount;
    }

    public void setGalleryCount(Integer galleryCount) {
        this.galleryCount = galleryCount;
    }

    public BigDecimal getAvgPage() {
        return avgPage;
    }

    public void setAvgPage(BigDecimal avgPage) {
        this.avgPage = avgPage;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public AboDto getTopCreator() {
        return topCreator;
    }

    public void setTopCreator(AboDto topCreator) {
        this.topCreator = topCreator;
    }
}
