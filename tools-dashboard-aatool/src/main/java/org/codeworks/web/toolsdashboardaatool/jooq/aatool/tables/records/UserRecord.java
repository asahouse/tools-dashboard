/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.User;
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
        "jOOQ version:3.9.2"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record8<Integer, Integer, String, String, String, LocalDateTime, String, String> {

    private static final long serialVersionUID = 1995925716;

    /**
     * Setter for <code>aatool.user.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>aatool.user.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>aatool.user.isAbo</code>.
     */
    public void setIsabo(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>aatool.user.isAbo</code>.
     */
    public Integer getIsabo() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>aatool.user.openId</code>.
     */
    public void setOpenid(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>aatool.user.openId</code>.
     */
    public String getOpenid() {
        return (String) get(2);
    }

    /**
     * Setter for <code>aatool.user.nickName</code>.
     */
    public void setNickname(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>aatool.user.nickName</code>.
     */
    public String getNickname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>aatool.user.headImgUrl</code>.
     */
    public void setHeadimgurl(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>aatool.user.headImgUrl</code>.
     */
    public String getHeadimgurl() {
        return (String) get(4);
    }

    /**
     * Setter for <code>aatool.user.createTime</code>.
     */
    public void setCreatetime(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>aatool.user.createTime</code>.
     */
    public LocalDateTime getCreatetime() {
        return (LocalDateTime) get(5);
    }

    /**
     * Setter for <code>aatool.user.inviteCode</code>.
     */
    public void setInvitecode(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>aatool.user.inviteCode</code>.
     */
    public String getInvitecode() {
        return (String) get(6);
    }

    /**
     * Setter for <code>aatool.user.ada</code>.
     */
    public void setAda(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>aatool.user.ada</code>.
     */
    public String getAda() {
        return (String) get(7);
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
    public Row8<Integer, Integer, String, String, String, LocalDateTime, String, String> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row8<Integer, Integer, String, String, String, LocalDateTime, String, String> valuesRow() {
        return (Row8) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return User.USER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return User.USER.ISABO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return User.USER.OPENID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return User.USER.NICKNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field5() {
        return User.USER.HEADIMGURL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field6() {
        return User.USER.CREATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field7() {
        return User.USER.INVITECODE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field8() {
        return User.USER.ADA;
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
        return getIsabo();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getOpenid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getNickname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value5() {
        return getHeadimgurl();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value6() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value7() {
        return getInvitecode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value8() {
        return getAda();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value2(Integer value) {
        setIsabo(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value3(String value) {
        setOpenid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value4(String value) {
        setNickname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value5(String value) {
        setHeadimgurl(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value6(LocalDateTime value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value7(String value) {
        setInvitecode(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord value8(String value) {
        setAda(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserRecord values(Integer value1, Integer value2, String value3, String value4, String value5, LocalDateTime value6, String value7, String value8) {
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
     * Create a detached UserRecord
     */
    public UserRecord() {
        super(User.USER);
    }

    /**
     * Create a detached, initialised UserRecord
     */
    public UserRecord(Integer id, Integer isabo, String openid, String nickname, String headimgurl, LocalDateTime createtime, String invitecode, String ada) {
        super(User.USER);

        set(0, id);
        set(1, isabo);
        set(2, openid);
        set(3, nickname);
        set(4, headimgurl);
        set(5, createtime);
        set(6, invitecode);
        set(7, ada);
    }
}
