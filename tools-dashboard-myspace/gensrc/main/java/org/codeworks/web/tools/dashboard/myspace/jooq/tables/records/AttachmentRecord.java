/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables.records;


import java.sql.Timestamp;

import javax.annotation.Generated;

import org.codeworks.web.tools.dashboard.myspace.jooq.tables.Attachment;
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
public class AttachmentRecord extends UpdatableRecordImpl<AttachmentRecord> implements Record5<Integer, String, String, Integer, Timestamp> {

    private static final long serialVersionUID = -209494133;

    /**
     * Setter for <code>myspace2.attachment.id</code>.
     */
    public AttachmentRecord setId(Integer value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.attachment.id</code>.
     */
    public Integer getId() {
        return (Integer) get(0);
    }

    /**
     * Setter for <code>myspace2.attachment.attKey</code>.
     */
    public AttachmentRecord setAttkey(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.attachment.attKey</code>.
     */
    public String getAttkey() {
        return (String) get(1);
    }

    /**
     * Setter for <code>myspace2.attachment.attName</code>.
     */
    public AttachmentRecord setAttname(String value) {
        set(2, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.attachment.attName</code>.
     */
    public String getAttname() {
        return (String) get(2);
    }

    /**
     * Setter for <code>myspace2.attachment.type</code>.
     */
    public AttachmentRecord setType(Integer value) {
        set(3, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.attachment.type</code>.
     */
    public Integer getType() {
        return (Integer) get(3);
    }

    /**
     * Setter for <code>myspace2.attachment.createTime</code>.
     */
    public AttachmentRecord setCreatetime(Timestamp value) {
        set(4, value);
        return this;
    }

    /**
     * Getter for <code>myspace2.attachment.createTime</code>.
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
    public Row5<Integer, String, String, Integer, Timestamp> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row5<Integer, String, String, Integer, Timestamp> valuesRow() {
        return (Row5) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field1() {
        return Attachment.ATTACHMENT.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Attachment.ATTACHMENT.ATTKEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field3() {
        return Attachment.ATTACHMENT.ATTNAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Integer> field4() {
        return Attachment.ATTACHMENT.TYPE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Timestamp> field5() {
        return Attachment.ATTACHMENT.CREATETIME;
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
    public String value2() {
        return getAttkey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value3() {
        return getAttname();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer value4() {
        return getType();
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
    public AttachmentRecord value1(Integer value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttachmentRecord value2(String value) {
        setAttkey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttachmentRecord value3(String value) {
        setAttname(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttachmentRecord value4(Integer value) {
        setType(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttachmentRecord value5(Timestamp value) {
        setCreatetime(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AttachmentRecord values(Integer value1, String value2, String value3, Integer value4, Timestamp value5) {
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
     * Create a detached AttachmentRecord
     */
    public AttachmentRecord() {
        super(Attachment.ATTACHMENT);
    }

    /**
     * Create a detached, initialised AttachmentRecord
     */
    public AttachmentRecord(Integer id, String attkey, String attname, Integer type, Timestamp createtime) {
        super(Attachment.ATTACHMENT);

        set(0, id);
        set(1, attkey);
        set(2, attname);
        set(3, type);
        set(4, createtime);
    }
}
