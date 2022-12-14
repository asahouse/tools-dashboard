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
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.UserinfoRecord;
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
public class Userinfo extends TableImpl<UserinfoRecord> {

    private static final long serialVersionUID = -83510328;

    /**
     * The reference instance of <code>aatool.userinfo</code>
     */
    public static final Userinfo USERINFO = new Userinfo();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserinfoRecord> getRecordType() {
        return UserinfoRecord.class;
    }

    /**
     * The column <code>aatool.userinfo.id</code>.
     */
    public final TableField<UserinfoRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.userinfo.userId</code>.
     */
    public final TableField<UserinfoRecord, Integer> USERID = createField("userId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.userinfo.name</code>.
     */
    public final TableField<UserinfoRecord, String> NAME = createField("name", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.userinfo.phone</code>.
     */
    public final TableField<UserinfoRecord, String> PHONE = createField("phone", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.userinfo.createTime</code>.
     */
    public final TableField<UserinfoRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>aatool.userinfo</code> table reference
     */
    public Userinfo() {
        this("userinfo", null);
    }

    /**
     * Create an aliased <code>aatool.userinfo</code> table reference
     */
    public Userinfo(String alias) {
        this(alias, USERINFO);
    }

    private Userinfo(String alias, Table<UserinfoRecord> aliased) {
        this(alias, aliased, null);
    }

    private Userinfo(String alias, Table<UserinfoRecord> aliased, Field<?>[] parameters) {
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
    public Identity<UserinfoRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USERINFO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserinfoRecord> getPrimaryKey() {
        return Keys.KEY_USERINFO_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserinfoRecord>> getKeys() {
        return Arrays.<UniqueKey<UserinfoRecord>>asList(Keys.KEY_USERINFO_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Userinfo as(String alias) {
        return new Userinfo(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Userinfo rename(String name) {
        return new Userinfo(name, null);
    }
}
