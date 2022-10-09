/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.daos;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.AreaActivityRegistrationForm;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.AreaActivityRegistrationFormRecord;
import org.jooq.Configuration;
import org.jooq.impl.DAOImpl;


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
public class AreaActivityRegistrationFormDao extends DAOImpl<AreaActivityRegistrationFormRecord, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm, Integer> {

    /**
     * Create a new AreaActivityRegistrationFormDao without any configuration
     */
    public AreaActivityRegistrationFormDao() {
        super(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm.class);
    }

    /**
     * Create a new AreaActivityRegistrationFormDao with an attached configuration
     */
    public AreaActivityRegistrationFormDao(Configuration configuration) {
        super(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm> fetchById(Integer... values) {
        return fetch(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm fetchOneById(Integer value) {
        return fetchOne(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.ID, value);
    }

    /**
     * Fetch records that have <code>batchId IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm> fetchByBatchid(Integer... values) {
        return fetch(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.BATCHID, values);
    }

    /**
     * Fetch records that have <code>userId IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm> fetchByUserid(Integer... values) {
        return fetch(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.USERID, values);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm> fetchByName(String... values) {
        return fetch(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.NAME, values);
    }

    /**
     * Fetch records that have <code>ada IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm> fetchByAda(String... values) {
        return fetch(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.ADA, values);
    }

    /**
     * Fetch records that have <code>createTime IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.AreaActivityRegistrationForm> fetchByCreatetime(LocalDateTime... values) {
        return fetch(AreaActivityRegistrationForm.AREA_ACTIVITY_REGISTRATION_FORM.CREATETIME, values);
    }
}
