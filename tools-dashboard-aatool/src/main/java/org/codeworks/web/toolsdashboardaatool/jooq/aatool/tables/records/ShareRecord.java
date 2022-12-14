/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Share;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record21;
import org.jooq.Row21;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ShareRecord extends UpdatableRecordImpl<ShareRecord> implements Record21<Integer, Integer, Integer, String, Integer, Integer, Integer, Integer, Integer, String, String, String, String, String, String, String, String, String, Integer, Integer, LocalDateTime> {

    private static final long serialVersionUID = 904803226;

    /**
     * Setter for <code>aatool.share.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>aatool.share.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>aatool.share.userId</code>.
     */
    public void setUserid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>aatool.share.userId</code>.
     */
    public Integer getUserid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>aatool.share.userType</code>.
     */
    public void setUsertype(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>aatool.share.userType</code>.
     */
    public Integer getUsertype() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>aatool.share.scene</code>.
     */
    public void setScene(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>aatool.share.scene</code>.
     */
    public String getScene() {
        return (String) get(3);
    }

    /**
     * Setter for <code>aatool.share.handlerUserId</code>.
     */
    public void setHandleruserid(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>aatool.share.handlerUserId</code>.
     */
    public Integer getHandleruserid() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>aatool.share.handlerShareId</code>.
     */
    public void setHandlershareid(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>aatool.share.handlerShareId</code>.
     */
    public Integer getHandlershareid() {
        return (Integer) get(5);
    }

    /**
     * Setter for <code>aatool.share.beforeShareId</code>.
     */
    public void setBeforeshareid(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>aatool.share.beforeShareId</code>.
     */
    public Integer getBeforeshareid() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>aatool.share.isCoverWorkshop</code>.
     */
    public void setIscoverworkshop(Integer value) {
        set(7, value);
    }

    /**
     * Getter for <code>aatool.share.isCoverWorkshop</code>.
     */
    public Integer getIscoverworkshop() {
        return (Integer) get(7);
    }

    /**
     * Setter for <code>aatool.share.inviteUserId</code>.
     */
    public void setInviteuserid(Integer value) {
        set(8, value);
    }

    /**
     * Getter for <code>aatool.share.inviteUserId</code>.
     */
    public Integer getInviteuserid() {
        return (Integer) get(8);
    }

    /**
     * Setter for <code>aatool.share.inviteCode</code>.
     */
    public void setInvitecode(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>aatool.share.inviteCode</code>.
     */
    public String getInvitecode() {
        return (String) get(9);
    }

    /**
     * Setter for <code>aatool.share.ada</code>.
     */
    public void setAda(String value) {
        set(10, value);
    }

    /**
     * Getter for <code>aatool.share.ada</code>.
     */
    public String getAda() {
        return (String) get(10);
    }

    /**
     * Setter for <code>aatool.share.workshopUrl</code>.
     */
    public void setWorkshopurl(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>aatool.share.workshopUrl</code>.
     */
    public String getWorkshopurl() {
        return (String) get(11);
    }

    /**
     * Setter for <code>aatool.share.workshopName</code>.
     */
    public void setWorkshopname(String value) {
        set(12, value);
    }

    /**
     * Getter for <code>aatool.share.workshopName</code>.
     */
    public String getWorkshopname() {
        return (String) get(12);
    }

    /**
     * Setter for <code>aatool.share.workshopHeadImgUrl</code>.
     */
    public void setWorkshopheadimgurl(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>aatool.share.workshopHeadImgUrl</code>.
     */
    public String getWorkshopheadimgurl() {
        return (String) get(13);
    }

    /**
     * Setter for <code>aatool.share.workshopWelcomeWord</code>.
     */
    public void setWorkshopwelcomeword(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>aatool.share.workshopWelcomeWord</code>.
     */
    public String getWorkshopwelcomeword() {
        return (String) get(14);
    }

    /**
     * Setter for <code>aatool.share.campaignIds</code>.
     */
    public void setCampaignids(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>aatool.share.campaignIds</code>.
     */
    public String getCampaignids() {
        return (String) get(15);
    }

    /**
     * Setter for <code>aatool.share.shareTitle</code>.
     */
    public void setSharetitle(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>aatool.share.shareTitle</code>.
     */
    public String getSharetitle() {
        return (String) get(16);
    }

    /**
     * Setter for <code>aatool.share.shareDigest</code>.
     */
    public void setSharedigest(String value) {
        set(17, value);
    }

    /**
     * Getter for <code>aatool.share.shareDigest</code>.
     */
    public String getSharedigest() {
        return (String) get(17);
    }

    /**
     * Setter for <code>aatool.share.status</code>.
     */
    public void setStatus(Integer value) {
        set(18, value);
    }

    /**
     * Getter for <code>aatool.share.status</code>.
     */
    public Integer getStatus() {
        return (Integer) get(18);
    }

    /**
     * Setter for <code>aatool.share.initialUserId</code>.
     */
    public void setInitialuserid(Integer value) {
        set(19, value);
    }

    /**
     * Getter for <code>aatool.share.initialUserId</code>.
     */
    public Integer getInitialuserid() {
        return (Integer) get(19);
    }

    /**
     * Setter for <code>aatool.share.createTime</code>.
     */
    public void setCreatetime(LocalDateTime value) {
        set(20, value);
    }

    /**
     * Getter for <code>aatool.share.createTime</code>.
     */
    public LocalDateTime getCreatetime() {
        return (LocalDateTime) get(20);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Integer> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record21 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row21<Integer, Integer, Integer, String, Integer, Integer, Integer, Integer, Integer, String, String, String, String, String, String, String, String, String, Integer, Integer, LocalDateTime> fieldsRow() {
        return (Row21) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row21<Integer, Integer, Integer, String, Integer, Integer, Integer, Integer, Integer, String, String, String, String, String, String, String, String, String, Integer, Integer, LocalDateTime> valuesRow() {
        return (Row21) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Share.SHARE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Share.SHARE.USERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Share.SHARE.USERTYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Share.SHARE.SCENE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Share.SHARE.HANDLERUSERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Share.SHARE.HANDLERSHAREID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field7() {
        return Share.SHARE.BEFORESHAREID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field8() {
        return Share.SHARE.ISCOVERWORKSHOP;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field9() {
        return Share.SHARE.INVITEUSERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field10() {
        return Share.SHARE.INVITECODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field11() {
        return Share.SHARE.ADA;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field12() {
        return Share.SHARE.WORKSHOPURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field13() {
        return Share.SHARE.WORKSHOPNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field14() {
        return Share.SHARE.WORKSHOPHEADIMGURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field15() {
        return Share.SHARE.WORKSHOPWELCOMEWORD;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field16() {
        return Share.SHARE.CAMPAIGNIDS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field17() {
        return Share.SHARE.SHARETITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field18() {
        return Share.SHARE.SHAREDIGEST;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field19() {
        return Share.SHARE.STATUS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field20() {
        return Share.SHARE.INITIALUSERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field21() {
        return Share.SHARE.CREATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value2() {
        return getUserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getUsertype();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getScene();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getHandleruserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getHandlershareid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value7() {
        return getBeforeshareid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value8() {
        return getIscoverworkshop();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value9() {
        return getInviteuserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value10() {
        return getInvitecode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value11() {
        return getAda();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value12() {
        return getWorkshopurl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value13() {
        return getWorkshopname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value14() {
        return getWorkshopheadimgurl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value15() {
        return getWorkshopwelcomeword();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value16() {
        return getCampaignids();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value17() {
        return getSharetitle();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value18() {
        return getSharedigest();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value19() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value20() {
        return getInitialuserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value21() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value2(Integer value) {
        setUserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value3(Integer value) {
        setUsertype(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value4(String value) {
        setScene(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value5(Integer value) {
        setHandleruserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value6(Integer value) {
        setHandlershareid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value7(Integer value) {
        setBeforeshareid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value8(Integer value) {
        setIscoverworkshop(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value9(Integer value) {
        setInviteuserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value10(String value) {
        setInvitecode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value11(String value) {
        setAda(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value12(String value) {
        setWorkshopurl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value13(String value) {
        setWorkshopname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value14(String value) {
        setWorkshopheadimgurl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value15(String value) {
        setWorkshopwelcomeword(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value16(String value) {
        setCampaignids(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value17(String value) {
        setSharetitle(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value18(String value) {
        setSharedigest(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value19(Integer value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value20(Integer value) {
        setInitialuserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord value21(LocalDateTime value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ShareRecord values(Integer value1, Integer value2, Integer value3, String value4, Integer value5, Integer value6, Integer value7, Integer value8, Integer value9, String value10, String value11, String value12, String value13, String value14, String value15, String value16, String value17, String value18, Integer value19, Integer value20, LocalDateTime value21) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        value18(value18);
        value19(value19);
        value20(value20);
        value21(value21);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ShareRecord
     */
    public ShareRecord() {
        super(Share.SHARE);
    }

    /**
     * Create a detached, initialised ShareRecord
     */
    public ShareRecord(Integer id, Integer userid, Integer usertype, String scene, Integer handleruserid, Integer handlershareid, Integer beforeshareid, Integer iscoverworkshop, Integer inviteuserid, String invitecode, String ada, String workshopurl, String workshopname, String workshopheadimgurl, String workshopwelcomeword, String campaignids, String sharetitle, String sharedigest, Integer status, Integer initialuserid, LocalDateTime createtime) {
        super(Share.SHARE);

        set(0, id);
        set(1, userid);
        set(2, usertype);
        set(3, scene);
        set(4, handleruserid);
        set(5, handlershareid);
        set(6, beforeshareid);
        set(7, iscoverworkshop);
        set(8, inviteuserid);
        set(9, invitecode);
        set(10, ada);
        set(11, workshopurl);
        set(12, workshopname);
        set(13, workshopheadimgurl);
        set(14, workshopwelcomeword);
        set(15, campaignids);
        set(16, sharetitle);
        set(17, sharedigest);
        set(18, status);
        set(19, initialuserid);
        set(20, createtime);
    }
}
