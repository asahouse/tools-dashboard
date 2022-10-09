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
public class Connection implements Serializable {

    private static final long serialVersionUID = -1720023500;

    private Integer       id;
    private Integer       userid;
    private Integer       shareid;
    private Integer       campaignid;
    private String        createtype;
    private LocalDateTime createtime;

    public Connection() {}

    public Connection(Connection value) {
        this.id = value.id;
        this.userid = value.userid;
        this.shareid = value.shareid;
        this.campaignid = value.campaignid;
        this.createtype = value.createtype;
        this.createtime = value.createtime;
    }

    public Connection(
        Integer       id,
        Integer       userid,
        Integer       shareid,
        Integer       campaignid,
        String        createtype,
        LocalDateTime createtime
    ) {
        this.id = id;
        this.userid = userid;
        this.shareid = shareid;
        this.campaignid = campaignid;
        this.createtype = createtype;
        this.createtime = createtime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
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

    public String getCreatetype() {
        return this.createtype;
    }

    public void setCreatetype(String createtype) {
        this.createtype = createtype;
    }

    public LocalDateTime getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Connection (");

        sb.append(id);
        sb.append(", ").append(userid);
        sb.append(", ").append(shareid);
        sb.append(", ").append(campaignid);
        sb.append(", ").append(createtype);
        sb.append(", ").append(createtime);

        sb.append(")");
        return sb.toString();
    }
}
