/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Apply;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.AreaActivityRegistrationForm;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Campaign;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Connection;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Register;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Report;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Share;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Template;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.User;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Userinfo;
import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


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
public class Aatool extends SchemaImpl {

    private static final long serialVersionUID = 747742788;

    /**
     * The reference instance of <code>aatool</code>
     */
    public static final Aatool AATOOL = new Aatool();

    /**
     * The table <code>aatool.apply</code>.
     */
    public final Apply APPLY = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Apply.APPLY;

    /**
     * The table <code>aatool.area_activity_registration_form</code>.
     */
    public final AreaActivityRegistrationForm AREA_ACTIVITY_REGISTRATION_FORM = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM;

    /**
     * The table <code>aatool.campaign</code>.
     */
    public final Campaign CAMPAIGN = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Campaign.CAMPAIGN;

    /**
     * The table <code>aatool.connection</code>.
     */
    public final Connection CONNECTION = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Connection.CONNECTION;

    /**
     * The table <code>aatool.register</code>.
     */
    public final Register REGISTER = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Register.REGISTER;

    /**
     * The table <code>aatool.report</code>.
     */
    public final Report REPORT = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Report.REPORT;

    /**
     * The table <code>aatool.share</code>.
     */
    public final Share SHARE = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Share.SHARE;

    /**
     * The table <code>aatool.template</code>.
     */
    public final Template TEMPLATE = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Template.TEMPLATE;

    /**
     * The table <code>aatool.user</code>.
     */
    public final User USER = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.User.USER;

    /**
     * The table <code>aatool.userinfo</code>.
     */
    public final Userinfo USERINFO = org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Userinfo.USERINFO;

    /**
     * No further instances allowed
     */
    private Aatool() {
        super("aatool", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            Apply.APPLY,
            AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM,
            Campaign.CAMPAIGN,
            Connection.CONNECTION,
            Register.REGISTER,
            Report.REPORT,
            Share.SHARE,
            Template.TEMPLATE,
            User.USER,
            Userinfo.USERINFO);
    }
}