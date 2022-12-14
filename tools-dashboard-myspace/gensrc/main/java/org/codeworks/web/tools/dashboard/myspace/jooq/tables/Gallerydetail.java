/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables;


import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.tools.dashboard.myspace.jooq.Keys;
import org.codeworks.web.tools.dashboard.myspace.jooq.Myspace2;
import org.codeworks.web.tools.dashboard.myspace.jooq.tables.records.GallerydetailRecord;
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
public class Gallerydetail extends TableImpl<GallerydetailRecord> {

    private static final long serialVersionUID = 1419694382;

    /**
     * The reference instance of <code>myspace2.gallerydetail</code>
     */
    public static final Gallerydetail GALLERYDETAIL = new Gallerydetail();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<GallerydetailRecord> getRecordType() {
        return GallerydetailRecord.class;
    }

    /**
     * The column <code>myspace2.gallerydetail.id</code>.
     */
    public final TableField<GallerydetailRecord, Integer> ID = createField("id", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>myspace2.gallerydetail.galleryId</code>.
     */
    public final TableField<GallerydetailRecord, Integer> GALLERYID = createField("galleryId", org.jooq.impl.SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>myspace2.gallerydetail.isCover</code>.
     */
    public final TableField<GallerydetailRecord, Integer> ISCOVER = createField("isCover", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>myspace2.gallerydetail.status</code>.
     */
    public final TableField<GallerydetailRecord, Integer> STATUS = createField("status", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>myspace2.gallerydetail.description</code>.
     */
    public final TableField<GallerydetailRecord, String> DESCRIPTION = createField("description", org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * The column <code>myspace2.gallerydetail.galleryKey</code>.
     */
    public final TableField<GallerydetailRecord, String> GALLERYKEY = createField("galleryKey", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * The column <code>myspace2.gallerydetail.smallGalleryKey</code>.
     */
    public final TableField<GallerydetailRecord, String> SMALLGALLERYKEY = createField("smallGalleryKey", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * The column <code>myspace2.gallerydetail.orderNo</code>.
     */
    public final TableField<GallerydetailRecord, Integer> ORDERNO = createField("orderNo", org.jooq.impl.SQLDataType.INTEGER.nullable(false).defaultValue(org.jooq.impl.DSL.inline("1", org.jooq.impl.SQLDataType.INTEGER)), this, "");

    /**
     * The column <code>myspace2.gallerydetail.chooseTemplate</code>.
     */
    public final TableField<GallerydetailRecord, String> CHOOSETEMPLATE = createField("chooseTemplate", org.jooq.impl.SQLDataType.VARCHAR.length(255), this, "");

    /**
     * Create a <code>myspace2.gallerydetail</code> table reference
     */
    public Gallerydetail() {
        this("gallerydetail", null);
    }

    /**
     * Create an aliased <code>myspace2.gallerydetail</code> table reference
     */
    public Gallerydetail(String alias) {
        this(alias, GALLERYDETAIL);
    }

    private Gallerydetail(String alias, Table<GallerydetailRecord> aliased) {
        this(alias, aliased, null);
    }

    private Gallerydetail(String alias, Table<GallerydetailRecord> aliased, Field<?>[] parameters) {
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
    public Identity<GallerydetailRecord, Integer> getIdentity() {
        return Keys.IDENTITY_GALLERYDETAIL;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<GallerydetailRecord> getPrimaryKey() {
        return Keys.KEY_GALLERYDETAIL_PRIMARY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<GallerydetailRecord>> getKeys() {
        return Arrays.<UniqueKey<GallerydetailRecord>>asList(Keys.KEY_GALLERYDETAIL_PRIMARY);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Gallerydetail as(String alias) {
        return new Gallerydetail(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Gallerydetail rename(String name) {
        return new Gallerydetail(name, null);
    }
}
