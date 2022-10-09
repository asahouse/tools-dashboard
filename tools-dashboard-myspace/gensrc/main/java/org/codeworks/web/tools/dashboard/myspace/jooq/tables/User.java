/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables;


import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.tools.dashboard.myspace.jooq.Keys;
import org.codeworks.web.tools.dashboard.myspace.jooq.Myspace2;
import org.codeworks.web.tools.dashboard.myspace.jooq.tables.records.UserRecord;
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
        "jOOQ version:3.9.6"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class User extends TableImpl<UserRecord> {

    private static final long serialVersionUID = -1697205808;

    /**
     * The reference instance of <code>myspace2.user</code>
     */
    public static final User USER = new User();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserRecord> getRecordType() {
        return UserRecord.class;
    }

    /**
     * The column <code>myspace2.user.id</code>.
     */
    public final TableField<UserRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>myspace2.user.isAbo</code>.
     */
    public final TableField<UserRecord, Integer> ISABO = createField("isAbo", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>myspace2.user.openId</code>.
     */
    public final TableField<UserRecord, String> OPENID = createField("openId", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>myspace2.user.nickName</code>.
     */
    public final TableField<UserRecord, String> NICKNAME = createField("nickName", org.jooq.impl.SQLDataType.VARCHAR.length(100).nullable(false), this, "");

    /**
     * The column <code>myspace2.user.headImgUrl</code>.
     */
    public final TableField<UserRecord, String> HEADIMGURL = createField("headImgUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>myspace2.user.createTime</code>.
     */
    public final TableField<UserRecord, Timestamp> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.TIMESTAMP.defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.TIMESTAMP)), this, "");

    /**
     * Create a <code>myspace2.user</code> table reference
     */
    public User() {
        this("user", null);
    }

    /**
     * Create an aliased <code>myspace2.user</code> table reference
     */
    public User(String alias) {
        this(alias, USER);
    }

    private User(String alias, Table<UserRecord> aliased) {
        this(alias, aliased, null);
    }

    private User(String alias, Table<UserRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Myspace2.MYSPACE2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Identity<UserRecord, Integer> getIdentity() {
        return Keys.IDENTITY_USER;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserRecord> getPrimaryKey() {
        return Keys.KEY_USER_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserRecord>> getKeys() {
        return Arrays.<UniqueKey<UserRecord>>asList(Keys.KEY_USER_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User as(String alias) {
        return new User(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public User rename(String name) {
        return new User(name, null);
    }
}
