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
public class Share implements Serializable {

    private static final long serialVersionUID = -254860914;

    private Integer       id;
    private Integer       userid;
    private Integer       usertype;
    private String        scene;
    private Integer       handleruserid;
    private Integer       handlershareid;
    private Integer       beforeshareid;
    private Integer       iscoverworkshop;
    private Integer       inviteuserid;
    private String        invitecode;
    private String        ada;
    private String        workshopurl;
    private String        workshopname;
    private String        workshopheadimgurl;
    private String        workshopwelcomeword;
    private String        campaignids;
    private String        sharetitle;
    private String        sharedigest;
    private Integer       status;
    private Integer       initialuserid;
    private LocalDateTime createtime;

    public Share() {}

    public Share(Share value) {
        this.id = value.id;
        this.userid = value.userid;
        this.usertype = value.usertype;
        this.scene = value.scene;
        this.handleruserid = value.handleruserid;
        this.handlershareid = value.handlershareid;
        this.beforeshareid = value.beforeshareid;
        this.iscoverworkshop = value.iscoverworkshop;
        this.inviteuserid = value.inviteuserid;
        this.invitecode = value.invitecode;
        this.ada = value.ada;
        this.workshopurl = value.workshopurl;
        this.workshopname = value.workshopname;
        this.workshopheadimgurl = value.workshopheadimgurl;
        this.workshopwelcomeword = value.workshopwelcomeword;
        this.campaignids = value.campaignids;
        this.sharetitle = value.sharetitle;
        this.sharedigest = value.sharedigest;
        this.status = value.status;
        this.initialuserid = value.initialuserid;
        this.createtime = value.createtime;
    }

    public Share(
        Integer       id,
        Integer       userid,
        Integer       usertype,
        String        scene,
        Integer       handleruserid,
        Integer       handlershareid,
        Integer       beforeshareid,
        Integer       iscoverworkshop,
        Integer       inviteuserid,
        String        invitecode,
        String        ada,
        String        workshopurl,
        String        workshopname,
        String        workshopheadimgurl,
        String        workshopwelcomeword,
        String        campaignids,
        String        sharetitle,
        String        sharedigest,
        Integer       status,
        Integer       initialuserid,
        LocalDateTime createtime
    ) {
        this.id = id;
        this.userid = userid;
        this.usertype = usertype;
        this.scene = scene;
        this.handleruserid = handleruserid;
        this.handlershareid = handlershareid;
        this.beforeshareid = beforeshareid;
        this.iscoverworkshop = iscoverworkshop;
        this.inviteuserid = inviteuserid;
        this.invitecode = invitecode;
        this.ada = ada;
        this.workshopurl = workshopurl;
        this.workshopname = workshopname;
        this.workshopheadimgurl = workshopheadimgurl;
        this.workshopwelcomeword = workshopwelcomeword;
        this.campaignids = campaignids;
        this.sharetitle = sharetitle;
        this.sharedigest = sharedigest;
        this.status = status;
        this.initialuserid = initialuserid;
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

    public Integer getUsertype() {
        return this.usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public String getScene() {
        return this.scene;
    }

    public void setScene(String scene) {
        this.scene = scene;
    }

    public Integer getHandleruserid() {
        return this.handleruserid;
    }

    public void setHandleruserid(Integer handleruserid) {
        this.handleruserid = handleruserid;
    }

    public Integer getHandlershareid() {
        return this.handlershareid;
    }

    public void setHandlershareid(Integer handlershareid) {
        this.handlershareid = handlershareid;
    }

    public Integer getBeforeshareid() {
        return this.beforeshareid;
    }

    public void setBeforeshareid(Integer beforeshareid) {
        this.beforeshareid = beforeshareid;
    }

    public Integer getIscoverworkshop() {
        return this.iscoverworkshop;
    }

    public void setIscoverworkshop(Integer iscoverworkshop) {
        this.iscoverworkshop = iscoverworkshop;
    }

    public Integer getInviteuserid() {
        return this.inviteuserid;
    }

    public void setInviteuserid(Integer inviteuserid) {
        this.inviteuserid = inviteuserid;
    }

    public String getInvitecode() {
        return this.invitecode;
    }

    public void setInvitecode(String invitecode) {
        this.invitecode = invitecode;
    }

    public String getAda() {
        return this.ada;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public String getWorkshopurl() {
        return this.workshopurl;
    }

    public void setWorkshopurl(String workshopurl) {
        this.workshopurl = workshopurl;
    }

    public String getWorkshopname() {
        return this.workshopname;
    }

    public void setWorkshopname(String workshopname) {
        this.workshopname = workshopname;
    }

    public String getWorkshopheadimgurl() {
        return this.workshopheadimgurl;
    }

    public void setWorkshopheadimgurl(String workshopheadimgurl) {
        this.workshopheadimgurl = workshopheadimgurl;
    }

    public String getWorkshopwelcomeword() {
        return this.workshopwelcomeword;
    }

    public void setWorkshopwelcomeword(String workshopwelcomeword) {
        this.workshopwelcomeword = workshopwelcomeword;
    }

    public String getCampaignids() {
        return this.campaignids;
    }

    public void setCampaignids(String campaignids) {
        this.campaignids = campaignids;
    }

    public String getSharetitle() {
        return this.sharetitle;
    }

    public void setSharetitle(String sharetitle) {
        this.sharetitle = sharetitle;
    }

    public String getSharedigest() {
        return this.sharedigest;
    }

    public void setSharedigest(String sharedigest) {
        this.sharedigest = sharedigest;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getInitialuserid() {
        return this.initialuserid;
    }

    public void setInitialuserid(Integer initialuserid) {
        this.initialuserid = initialuserid;
    }

    public LocalDateTime getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Share (");

        sb.append(id);
        sb.append(", ").append(userid);
        sb.append(", ").append(usertype);
        sb.append(", ").append(scene);
        sb.append(", ").append(handleruserid);
        sb.append(", ").append(handlershareid);
        sb.append(", ").append(beforeshareid);
        sb.append(", ").append(iscoverworkshop);
        sb.append(", ").append(inviteuserid);
        sb.append(", ").append(invitecode);
        sb.append(", ").append(ada);
        sb.append(", ").append(workshopurl);
        sb.append(", ").append(workshopname);
        sb.append(", ").append(workshopheadimgurl);
        sb.append(", ").append(workshopwelcomeword);
        sb.append(", ").append(campaignids);
        sb.append(", ").append(sharetitle);
        sb.append(", ").append(sharedigest);
        sb.append(", ").append(status);
        sb.append(", ").append(initialuserid);
        sb.append(", ").append(createtime);

        sb.append(")");
        return sb.toString();
    }
}
