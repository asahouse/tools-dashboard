/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records;


import java.time.LocalDateTime;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Register;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
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
public class RegisterRecord extends UpdatableRecordImpl<RegisterRecord> implements Record6<Integer, Integer, Integer, LocalDateTime, Integer, Integer> {

    private static final long serialVersionUID = 236066462;

    /**
     * Setter for <code>aatool.register.id</code>.
     */
    public void setId(Integer value) {
        set(0, value);
    }

    /**
     * Getter for <code>aatool.register.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>aatool.register.campaignId</code>.
     */
    public void setCampaignid(Integer value) {
        set(1, value);
    }

    /**
     * Getter for <code>aatool.register.campaignId</code>.
     */
    public Integer getCampaignid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>aatool.register.userId</code>.
     */
    public void setUserid(Integer value) {
        set(2, value);
    }

    /**
     * Getter for <code>aatool.register.userId</code>.
     */
    public Integer getUserid() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>aatool.register.createTime</code>.
     */
    public void setCreatetime(LocalDateTime value) {
        set(3, value);
    }

    /**
     * Getter for <code>aatool.register.createTime</code>.
     */
    public LocalDateTime getCreatetime() {
        return (LocalDateTime) get(3);
    }

    /**
     * Setter for <code>aatool.register.hasApply</code>.
     */
    public void setHasapply(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>aatool.register.hasApply</code>.
     */
    public Integer getHasapply() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>aatool.register.status</code>.
     */
    public void setStatus(Integer value) {
        set(5, value);
    }

    /**
     * Getter for <code>aatool.register.status</code>.
     */
    public Integer getStatus() {
        return (Integer) get(5);
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
    // Record6 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, LocalDateTime, Integer, Integer> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row6<Integer, Integer, Integer, LocalDateTime, Integer, Integer> valuesRow() {
        return (Row6) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Register.REGISTER.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Register.REGISTER.CAMPAIGNID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Register.REGISTER.USERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<LocalDateTime> field4() {
        return Register.REGISTER.CREATETIME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field5() {
        return Register.REGISTER.HASAPPLY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field6() {
        return Register.REGISTER.STATUS;
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
        return getCampaignid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value3() {
        return getUserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LocalDateTime value4() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value5() {
        return getHasapply();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value6() {
        return getStatus();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord value2(Integer value) {
        setCampaignid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord value3(Integer value) {
        setUserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord value4(LocalDateTime value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord value5(Integer value) {
        setHasapply(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord value6(Integer value) {
        setStatus(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public RegisterRecord values(Integer value1, Integer value2, Integer value3, LocalDateTime value4, Integer value5, Integer value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RegisterRecord
     */
    public RegisterRecord() {
        super(Register.REGISTER);
    }

    /**
     * Create a detached, initialised RegisterRecord
     */
    public RegisterRecord(Integer id, Integer campaignid, Integer userid, LocalDateTime createtime, Integer hasapply, Integer status) {
        super(Register.REGISTER);

        set(0, id);
        set(1, campaignid);
        set(2, userid);
        set(3, createtime);
        set(4, hasapply);
        set(5, status);
    }
}
