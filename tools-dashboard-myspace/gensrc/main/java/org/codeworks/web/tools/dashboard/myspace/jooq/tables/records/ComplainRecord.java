/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.codeworks.web.tools.dashboard.myspace.jooq.tables.Complain;
import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row5;
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
public class ComplainRecord extends UpdatableRecordImpl<ComplainRecord> implements Record5<Integer, Integer, Integer, String, Timestamp> {

    private static final long serialVersionUID = -757026626;

    /**
     * Setter for <code>myspace2.complain.id</code>.
     */
    public ComplainRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.complain.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>myspace2.complain.galleryId</code>.
     */
    public ComplainRecord setGalleryid(Integer value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.complain.galleryId</code>.
     */
    public Integer getGalleryid() {
        return (Integer) get(1);
    }

    /**
     * Setter for <code>myspace2.complain.userId</code>.
     */
    public ComplainRecord setUserid(Integer value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.complain.userId</code>.
     */
    public Integer getUserid() {
        return (Integer) get(2);
    }

    /**
     * Setter for <code>myspace2.complain.content</code>.
     */
    public ComplainRecord setContent(String value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.complain.content</code>.
     */
    public String getContent() {
        return (String) get(3);
    }

    /**
     * Setter for <code>myspace2.complain.createTime</code>.
     */
    public ComplainRecord setCreatetime(Timestamp value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.complain.createTime</code>.
     */
    public Timestamp getCreatetime() {
        return (Timestamp) get(4);
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
    // Record5 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, String, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, Integer, Integer, String, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Complain.COMPLAIN.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field2() {
        return Complain.COMPLAIN.GALLERYID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field3() {
        return Complain.COMPLAIN.USERID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field4() {
        return Complain.COMPLAIN.CONTENT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return Complain.COMPLAIN.CREATETIME;
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
        return getUserid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value4() {
        return getContent();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Timestamp value5() {
        return getCreatetime();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplainRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplainRecord value2(Integer value) {
        setGalleryid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplainRecord value3(Integer value) {
        setUserid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplainRecord value4(String value) {
        setContent(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplainRecord value5(Timestamp value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ComplainRecord values(Integer value1, Integer value2, Integer value3, String value4, Timestamp value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ComplainRecord
     */
    public ComplainRecord() {
        super(Complain.COMPLAIN);
    }

    /**
     * Create a detached, initialised ComplainRecord
     */
    public ComplainRecord(Integer id, Integer galleryid, Integer userid, String content, Timestamp createtime) {
        super(Complain.COMPLAIN);

        set(0, id);
        set(1, galleryid);
        set(2, userid);
        set(3, content);
        set(4, createtime);
    }
}