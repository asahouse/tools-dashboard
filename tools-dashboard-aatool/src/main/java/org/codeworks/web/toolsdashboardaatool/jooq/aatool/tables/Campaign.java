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
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.CampaignRecord;
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
public class Campaign extends TableImpl<CampaignRecord> {

    private static final long serialVersionUID = 2138196121;

    /**
     * The reference instance of <code>aatool.campaign</code>
     */
    public static final Campaign CAMPAIGN = new Campaign();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<CampaignRecord> getRecordType() {
        return CampaignRecord.class;
    }

    /**
     * The column <code>aatool.campaign.id</code>.
     */
    public final TableField<CampaignRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.title</code>.
     */
    public final TableField<CampaignRecord, String> TITLE = createField("title", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.location</code>.
     */
    public final TableField<CampaignRecord, String> LOCATION = createField("location", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.digest</code>.
     */
    public final TableField<CampaignRecord, String> DIGEST = createField("digest", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.applyDeadline</code>.
     */
    public final TableField<CampaignRecord, LocalDateTime> APPLYDEADLINE = createField("applyDeadline", org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false).defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>aatool.campaign.startTime</code>.
     */
    public final TableField<CampaignRecord, LocalDateTime> STARTTIME = createField("startTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>aatool.campaign.endTime</code>.
     */
    public final TableField<CampaignRecord, LocalDateTime> ENDTIME = createField("endTime", org.jooq.impl.SQLDataType.LOCALDATETIME, this, "");

    /**
     * The column <code>aatool.campaign.limit</code>.
     */
    public final TableField<CampaignRecord, Integer> LIMIT = createField("limit", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.inline("-1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.campaign.coverImageKey</code>.
     */
    public final TableField<CampaignRecord, String> COVERIMAGEKEY = createField("coverImageKey", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.chargeImageKey</code>.
     */
    public final TableField<CampaignRecord, String> CHARGEIMAGEKEY = createField("chargeImageKey", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.content</code>.
     */
    public final TableField<CampaignRecord, String> CONTENT = createField("content", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.canCoverWorkshop</code>.
     */
    public final TableField<CampaignRecord, Integer> CANCOVERWORKSHOP = createField("canCoverWorkshop", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.campaign.isCharge</code>.
     */
    public final TableField<CampaignRecord, Integer> ISCHARGE = createField("isCharge", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.campaign.templateId</code>.
     */
    public final TableField<CampaignRecord, Integer> TEMPLATEID = createField("templateId", org.jooq.impl.SQLDataType.INTEGER.defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.campaign.ada</code>.
     */
    public final TableField<CampaignRecord, String> ADA = createField("ada", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.workshopUrl</code>.
     */
    public final TableField<CampaignRecord, String> WORKSHOPURL = createField("workshopUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.workshopName</code>.
     */
    public final TableField<CampaignRecord, String> WORKSHOPNAME = createField("workshopName", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.workshopHeadImgUrl</code>.
     */
    public final TableField<CampaignRecord, String> WORKSHOPHEADIMGURL = createField("workshopHeadImgUrl", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.workshopWelcomeWord</code>.
     */
    public final TableField<CampaignRecord, String> WORKSHOPWELCOMEWORD = createField("workshopWelcomeWord", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.status</code>.
     */
    public final TableField<CampaignRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>aatool.campaign.createUserId</code>.
     */
    public final TableField<CampaignRecord, Integer> CREATEUSERID = createField("createUserId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.createTime</code>.
     */
    public final TableField<CampaignRecord, LocalDateTime> CREATETIME = createField("createTime", org.jooq.impl.SQLDataType.LOCALDATETIME.defaultValue(org.jooq.impl.DSL.inline("CURRENT_TIMESTAMP", org.jooq.impl.SQLDataType.LOCALDATETIME)), this, "");

    /**
     * The column <code>aatool.campaign.shareDigest</code>.
     */
    public final TableField<CampaignRecord, String> SHAREDIGEST = createField("shareDigest", org.jooq.impl.SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>aatool.campaign.organizers</code>.
     */
    public final TableField<CampaignRecord, String> ORGANIZERS = createField("organizers", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>aatool.campaign.ticket</code>.
     */
    public final TableField<CampaignRecord, String> TICKET = createField("ticket", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * Create a <code>aatool.campaign</code> table reference
     */
    public Campaign() {
        this("campaign", null);
    }

    /**
     * Create an aliased <code>aatool.campaign</code> table reference
     */
    public Campaign(String alias) {
        this(alias, CAMPAIGN);
    }

    private Campaign(String alias, Table<CampaignRecord> aliased) {
        this(alias, aliased, null);
    }

    private Campaign(String alias, Table<CampaignRecord> aliased, Field<?>[] parameters) {
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
    public Identity<CampaignRecord, Integer> getIdentity() {
        return Keys.IDENTITY_CAMPAIGN;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<CampaignRecord> getPrimaryKey() {
        return Keys.KEY_CAMPAIGN_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<CampaignRecord>> getKeys() {
        return Arrays.<UniqueKey<CampaignRecord>>asList(Keys.KEY_CAMPAIGN_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Campaign as(String alias) {
        return new Campaign(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Campaign rename(String name) {
        return new Campaign(name, null);
    }
}
