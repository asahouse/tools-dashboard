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
public class Apply implements Serializable {

    private static final long serialVersionUID = -1982004844;

    private Integer       id;
    private Integer       shareid;
    private Integer       campaignid;
    private Integer       userid;
    private String        memo;
    private LocalDateTime createtime;
    private Integer       status;

    public Apply() {}

    public Apply(Apply value) {
        this.id = value.id;
        this.shareid = value.shareid;
        this.campaignid = value.campaignid;
        this.userid = value.userid;
        this.memo = value.memo;
        this.createtime = value.createtime;
        this.status = value.status;
    }

    public Apply(
        Integer       id,
        Integer       shareid,
        Integer       campaignid,
        Integer       userid,
        String        memo,
        LocalDateTime createtime,
        Integer       status
    ) {
        this.id = id;
        this.shareid = shareid;
        this.campaignid = campaignid;
        this.userid = userid;
        this.memo = memo;
        this.createtime = createtime;
        this.status = status;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShareid() {
        return this.shareid;
    }

    public void setShareid(Integer shareid) {
        this.shareid = shareid;
    }

    public Integer getCampaignid() {
        return this.campaignid;
    }

    public void setCampaignid(Integer campaignid) {
        this.campaignid = campaignid;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getMemo() {
        return this.memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public LocalDateTime getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Apply (");

        sb.append(id);
        sb.append(", ").append(shareid);
        sb.append(", ").append(campaignid);
        sb.append(", ").append(userid);
        sb.append(", ").append(memo);
        sb.append(", ").append(createtime);
        sb.append(", ").append(status);

        sb.append(")");
        return sb.toString();
    }
}