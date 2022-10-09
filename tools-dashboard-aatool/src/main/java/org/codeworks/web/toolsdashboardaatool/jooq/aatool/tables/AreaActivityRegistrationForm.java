/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.Aatool;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.Keys;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.AreaActivityRegistrationFormRecord;
import org.jooq.Field;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


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
public class AreaActivityRegistrationForm extends TableImpl<AreaActivityRegistrationFormRecord> {

    private static final long serialVersionUID = 712716137;

    /**
     * The reference instance of <code>aatool.area_activity_registration_form</code>
     */
    public static final AreaActivityRegistrationForm AREA_ACTIVITY_REGISTRATION_FORM = new AreaActivityRegistrationForm();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AreaActivityRegistrationFormRecord> getRecordType() {
        return AreaActivityRegistrationFormRecord.class;
    }

    /**
     * The column <code>aatool.area_activity_registration_form.id</code>.
     */
    public final TableField<AreaActivityRegistrationFormRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.area_activity_registration_form.batchId</code>.
     */
    public final TableField<AreaActivityRegistrationFormRecord, Integer> BATCHID = createField("batchId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.area_activity_registration_form.userId</code>.
     */
    public final TableField<AreaActivityRegistrationFormRecord, Integer> USERID = createField("userId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.area_activity_registration_form.name</code>.
     */
    public final TableField<AreaActivityRegistrationFormRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>aatool.area_activity_registration_form.ada</code>.
     */
    public final TableField<AreaActivityRegistrationFormRecord, String> ADA = createField("ada", org.jooq.impl.SQLDataType.VARCHAR.length(45).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "");

    /**
     * The column <code>aatool.area_activity_registration_form.createTime</code>.
     */
    public final TableField<AreaActivityRegistrationFormRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>aatool.area_activity_registration_form</code> table reference
     */
    public AreaActivityRegistrationForm() {
        this("area_activity_registration_form", null);
    }

    /**
     * Create an aliased <code>aatool.area_activity_registration_form</code> table reference
     */
    public AreaActivityRegistrationForm(String alias) {
        this(alias, AREA_ACTIVITY_REGISTRATION_FORM);
    }

    private AreaActivityRegistrationForm(String alias, Table<AreaActivityRegistrationFormRecord> aliased) {
        this(alias, aliased, null);
    }

    private AreaActivityRegistrationForm(String alias, Table<AreaActivityRegistrationFormRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Aatool.AATOOL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<AreaActivityRegistrationFormRecord, Integer> getIdentity() {
        return Keys.IDENTITY_AREA_ACTIVITY_REGISTRATION_FORM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AreaActivityRegistrationFormRecord> getPrimaryKey() {
        return Keys.KEY_AREA_ACTIVITY_REGISTRATION_FORM_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AreaActivityRegistrationFormRecord>> getKeys() {
        return Arrays.<UniqueKey<AreaActivityRegistrationFormRecord>>asList(Keys.KEY_AREA_ACTIVITY_REGISTRATION_FORM_PRIMARY, Keys.KEY_AREA_ACTIVITY_REGISTRATION_FORM_BATCHIDANDUSERID);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AreaActivityRegistrationForm as(String alias) {
        return new AreaActivityRegistrationForm(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public AreaActivityRegistrationForm rename(String name) {
        return new AreaActivityRegistrationForm(name, null);
    }
}