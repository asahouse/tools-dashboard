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
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.ShareRecord;
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
public class Share extends TableImpl<ShareRecord> {

    private static final long serialVersionUID = -4011864;

    /**
     * The reference instance of <code>aatool.share</code>
     */
    public static final Share SHARE = new Share();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ShareRecord> getRecordType() {
        return ShareRecord.class;
    }

    /**
     * The column <code>aatool.share.id</code>.
     */
    public final TableField<ShareRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.share.userId</code>.
     */
    public final TableField<ShareRecord, Integer> USERID = createField("userId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.share.userType</code>.
     */
    public final TableField<ShareRecord, Integer> USERTYPE = createField("userType", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.share.scene</code>.
     */
    public final TableField<ShareRecord, String> SCENE = createField("scene", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>aatool.share.handlerUserId</code>.
     */
    public final TableField<ShareRecord, Integer> HANDLERUSERID = createField("handlerUserId", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>aatool.share.handlerShareId</code>.
     */
    public final TableField<ShareRecord, Integer> HANDLERSHAREID = createField("handlerShareId", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>aatool.share.beforeShareId</code>.
     */
    public final TableField<ShareRecord, Integer> BEFORESHAREID = createField("beforeShareId", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>aatool.share.isCoverWorkshop</code>.
     */
    public final TableField<ShareRecord, Integer> ISCOVERWORKSHOP = createField("isCoverWorkshop", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.share.inviteUserId</code>.
     */
    public final TableField<ShareRecord, Integer> INVITEUSERID = createField("inviteUserId", org.jooq.impl.SQLDataType.INTEGER, this, "");

    /**
     * The column <code>aatool.share.inviteCode</code>.
     */
    public final TableField<ShareRecord, String> INVITECODE = createField("inviteCode", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.ada</code>.
     */
    public final TableField<ShareRecord, String> ADA = createField("ada", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.workshopUrl</code>.
     */
    public final TableField<ShareRecord, String> WORKSHOPURL = createField("workshopUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.workshopName</code>.
     */
    public final TableField<ShareRecord, String> WORKSHOPNAME = createField("workshopName", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.workshopHeadImgUrl</code>.
     */
    public final TableField<ShareRecord, String> WORKSHOPHEADIMGURL = createField("workshopHeadImgUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.workshopWelcomeWord</code>.
     */
    public final TableField<ShareRecord, String> WORKSHOPWELCOMEWORD = createField("workshopWelcomeWord", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.campaignIds</code>.
     */
    public final TableField<ShareRecord, String> CAMPAIGNIDS = createField("campaignIds", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>aatool.share.shareTitle</code>.
     */
    public final TableField<ShareRecord, String> SHARETITLE = createField("shareTitle", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.shareDigest</code>.
     */
    public final TableField<ShareRecord, String> SHAREDIGEST = createField("shareDigest", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.share.status</code>.
     */
    public final TableField<ShareRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.share.initialUserId</code>.
     */
    public final TableField<ShareRecord, Integer> INITIALUSERID = createField("initialUserId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.share.createTime</code>.
     */
    public final TableField<ShareRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * Create a <code>aatool.share</code> table reference
     */
    public Share() {
        this("share", null);
    }

    /**
     * Create an aliased <code>aatool.share</code> table reference
     */
    public Share(String alias) {
        this(alias, SHARE);
    }

    private Share(String alias, Table<ShareRecord> aliased) {
        this(alias, aliased, null);
    }

    private Share(String alias, Table<ShareRecord> aliased, Field<?>[] parameters) {
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
    public Identity<ShareRecord, Integer> getIdentity() {
        return Keys.IDENTITY_SHARE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ShareRecord> getPrimaryKey() {
        return Keys.KEY_SHARE_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ShareRecord>> getKeys() {
        return Arrays.<UniqueKey<ShareRecord>>asList(Keys.KEY_SHARE_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Share as(String alias) {
        return new Share(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Share rename(String name) {
        return new Share(name, null);
    }
}
