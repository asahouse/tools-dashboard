/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Template implements Serializable {

    private static final long serialVersionUID = -1704480250;

    private Integer       id;
    private String        name;
    private Integer       sequence;
    private String        title;
    private String        location;
    private String        digest;
    private LocalDateTime applydeadline;
    private LocalDateTime starttime;
    private LocalDateTime endtime;
    private Integer       limit;
    private String        coverimagekey;
    private String        chargeimagekey;
    private String        content;
    private Integer       cancoverworkshop;
    private Integer       ischarge;
    private Integer       status;
    private LocalDateTime createtime;

    public Template() {}

    public Template(Template value) {
        this.id = value.id;
        this.name = value.name;
        this.sequence = value.sequence;
        this.title = value.title;
        this.location = value.location;
        this.digest = value.digest;
        this.applydeadline = value.applydeadline;
        this.starttime = value.starttime;
        this.endtime = value.endtime;
        this.limit = value.limit;
        this.coverimagekey = value.coverimagekey;
        this.chargeimagekey = value.chargeimagekey;
        this.content = value.content;
        this.cancoverworkshop = value.cancoverworkshop;
        this.ischarge = value.ischarge;
        this.status = value.status;
        this.createtime = value.createtime;
    }

    public Template(
        Integer       id,
        String        name,
        Integer       sequence,
        String        title,
        String        location,
        String        digest,
        LocalDateTime applydeadline,
        LocalDateTime starttime,
        LocalDateTime endtime,
        Integer       limit,
        String        coverimagekey,
        String        chargeimagekey,
        String        content,
        Integer       cancoverworkshop,
        Integer       ischarge,
        Integer       status,
        LocalDateTime createtime
    ) {
        this.id = id;
        this.name = name;
        this.sequence = sequence;
        this.title = title;
        this.location = location;
        this.digest = digest;
        this.applydeadline = applydeadline;
        this.starttime = starttime;
        this.endtime = endtime;
        this.limit = limit;
        this.coverimagekey = coverimagekey;
        this.chargeimagekey = chargeimagekey;
        this.content = content;
        this.cancoverworkshop = cancoverworkshop;
        this.ischarge = ischarge;
        this.status = status;
        this.createtime = createtime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return this.sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDigest() {
        return this.digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }

    public LocalDateTime getApplydeadline() {
        return this.applydeadline;
    }

    public void setApplydeadline(LocalDateTime applydeadline) {
        this.applydeadline = applydeadline;
    }

    public LocalDateTime getStarttime() {
        return this.starttime;
    }

    public void setStarttime(LocalDateTime starttime) {
        this.starttime = starttime;
    }

    public LocalDateTime getEndtime() {
        return this.endtime;
    }

    public void setEndtime(LocalDateTime endtime) {
        this.endtime = endtime;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getCoverimagekey() {
        return this.coverimagekey;
    }

    public void setCoverimagekey(String coverimagekey) {
        this.coverimagekey = coverimagekey;
    }

    public String getChargeimagekey() {
        return this.chargeimagekey;
    }

    public void setChargeimagekey(String chargeimagekey) {
        this.chargeimagekey = chargeimagekey;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCancoverworkshop() {
        return this.cancoverworkshop;
    }

    public void setCancoverworkshop(Integer cancoverworkshop) {
        this.cancoverworkshop = cancoverworkshop;
    }

    public Integer getIscharge() {
        return this.ischarge;
    }

    public void setIscharge(Integer ischarge) {
        this.ischarge = ischarge;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public LocalDateTime getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Template (");

        sb.append(id);
        sb.append(", ").append(name);
        sb.append(", ").append(sequence);
        sb.append(", ").append(title);
        sb.append(", ").append(location);
        sb.append(", ").append(digest);
        sb.append(", ").append(applydeadline);
        sb.append(", ").append(starttime);
        sb.append(", ").append(endtime);
        sb.append(", ").append(limit);
        sb.append(", ").append(coverimagekey);
        sb.append(", ").append(chargeimagekey);
        sb.append(", ").append(content);
        sb.append(", ").append(cancoverworkshop);
        sb.append(", ").append(ischarge);
        sb.append(", ").append(status);
        sb.append(", ").append(createtime);

        sb.append(")");
        return sb.toString();
    }
}
