/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables.pojos;


import java.io.Serializable;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Gallerydetail implements Serializable {

    private static final long serialVersionUID = 1468175170;

    private Integer id;
    private Integer galleryid;
    private Integer iscover;
    private Integer status;
    private String  description;
    private String  gallerykey;
    private String  smallgallerykey;
    private Integer orderno;
    private String  choosetemplate;

    public Gallerydetail() {}

    public Gallerydetail(Gallerydetail value) {
        this.id = value.id;
        this.galleryid = value.galleryid;
        this.iscover = value.iscover;
        this.status = value.status;
        this.description = value.description;
        this.gallerykey = value.gallerykey;
        this.smallgallerykey = value.smallgallerykey;
        this.orderno = value.orderno;
        this.choosetemplate = value.choosetemplate;
    }

    public Gallerydetail(
        Integer id,
        Integer galleryid,
        Integer iscover,
        Integer status,
        String  description,
        String  gallerykey,
        String  smallgallerykey,
        Integer orderno,
        String  choosetemplate
    ) {
        this.id = id;
        this.galleryid = galleryid;
        this.iscover = iscover;
        this.status = status;
        this.description = description;
        this.gallerykey = gallerykey;
        this.smallgallerykey = smallgallerykey;
        this.orderno = orderno;
        this.choosetemplate = choosetemplate;
    }

    public Integer getId() {
        return this.id;
    }

    public Gallerydetail setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getGalleryid() {
        return this.galleryid;
    }

    public Gallerydetail setGalleryid(Integer galleryid) {
        this.galleryid = galleryid;
        return this;
    }

    public Integer getIscover() {
        return this.iscover;
    }

    public Gallerydetail setIscover(Integer iscover) {
        this.iscover = iscover;
        return this;
    }

    public Integer getStatus() {
        return this.status;
    }

    public Gallerydetail setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getDescription() {
        return this.description;
    }

    public Gallerydetail setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getGallerykey() {
        return this.gallerykey;
    }

    public Gallerydetail setGallerykey(String gallerykey) {
        this.gallerykey = gallerykey;
        return this;
    }

    public String getSmallgallerykey() {
        return this.smallgallerykey;
    }

    public Gallerydetail setSmallgallerykey(String smallgallerykey) {
        this.smallgallerykey = smallgallerykey;
        return this;
    }

    public Integer getOrderno() {
        return this.orderno;
    }

    public Gallerydetail setOrderno(Integer orderno) {
        this.orderno = orderno;
        return this;
    }

    public String getChoosetemplate() {
        return this.choosetemplate;
    }

    public Gallerydetail setChoosetemplate(String choosetemplate) {
        this.choosetemplate = choosetemplate;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Gallerydetail (");

        sb.append(id);
        sb.append(", ").append(galleryid);
        sb.append(", ").append(iscover);
        sb.append(", ").append(status);
        sb.append(", ").append(description);
        sb.append(", ").append(gallerykey);
        sb.append(", ").append(smallgallerykey);
        sb.append(", ").append(orderno);
        sb.append(", ").append(choosetemplate);

        sb.append(")");
        return sb.toString();
    }
}
