/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.codeworks.web.tools.dashboard.myspace.jooq.tables.Clicklike;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ClicklikeRecord extends UpdatableRecordImpl<ClicklikeRecord> implements Record8<Integer, Integer, Integer, Integer, String, String, String, Timestamp> {

    private static final long serialVersionUID = 575721784;

    /**
     * Setter for <code>myspace2.clicklike.id</code>.
     */
    public ClicklikeRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>myspace2.clicklike.galleryId</code>.
     */
    public ClicklikeRecord setGalleryid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.galleryId</code>.
     */
    public Integer getGalleryid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>myspace2.clicklike.detailId</code>.
     */
    public ClicklikeRecord setDetailid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.detailId</code>.
     */
    public Integer getDetailid() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>myspace2.clicklike.userId</code>.
     */
    public ClicklikeRecord setUserid(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.userId</code>.
     */
    public Integer getUserid() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>myspace2.clicklike.openId</code>.
     */
    public ClicklikeRecord setOpenid(String value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.openId</code>.
     */
    public String getOpenid() {
        return (String) get(4);
    }

    /**
     * Setter for <code>myspace2.clicklike.nickName</code>.
     */
    public ClicklikeRecord setNickname(String value) {
        set(5, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.nickName</code>.
     */
    public String getNickname() {
        return (String) get(5);
    }

    /**
     * Setter for <code>myspace2.clicklike.headImgUrl</code>.
     */
    public ClicklikeRecord setHeadimgurl(String value) {
        set(6, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.headImgUrl</code>.
     */
    public String getHeadimgurl() {
        return (String) get(6);
    }

    /**
     * Setter for <code>myspace2.clicklike.createTime</code>.
     */
    public ClicklikeRecord setCreatetime(Timestamp value) {
        set(7, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.clicklike.createTime</code>.
     */
    public Timestamp getCreatetime() {
        return (Timestamp) get(7);
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
    // Record8 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, Integer, Integer, Integer, String, String, String, Timestamp> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, Integer, Integer, Integer, String, String, String, Timestamp> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Clicklike.CLICKLIKE.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Clicklike.CLICKLIKE.GALLERYID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Clicklike.CLICKLIKE.DETAILID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Clicklike.CLICKLIKE.USERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return Clicklike.CLICKLIKE.OPENID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field6() {
        return Clicklike.CLICKLIKE.NICKNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return Clicklike.CLICKLIKE.HEADIMGURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field8() {
        return Clicklike.CLICKLIKE.CREATETIME;
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
        return getGalleryid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getDetailid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getUserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getOpenid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value6() {
        return getNickname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getHeadimgurl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value8() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value2(Integer value) {
        setGalleryid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value3(Integer value) {
        setDetailid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value4(Integer value) {
        setUserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value5(String value) {
        setOpenid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value6(String value) {
        setNickname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value7(String value) {
        setHeadimgurl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord value8(Timestamp value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ClicklikeRecord values(Integer value1, Integer value2, Integer value3, Integer value4, String value5, String value6, String value7, Timestamp value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ClicklikeRecord
     */
    public ClicklikeRecord() {
        super(Clicklike.CLICKLIKE);
    }

    /**
     * Create a detached, initialised ClicklikeRecord
     */
    public ClicklikeRecord(Integer id, Integer galleryid, Integer detailid, Integer userid, String openid, String nickname, String headimgurl, Timestamp createtime) {
        super(Clicklike.CLICKLIKE);

        set(0, id);
        set(1, galleryid);
        set(2, detailid);
        set(3, userid);
        set(4, openid);
        set(5, nickname);
        set(6, headimgurl);
        set(7, createtime);
    }
}
