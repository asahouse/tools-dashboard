/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.daos;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Apply;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.ApplyRecord;
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
public class ApplyDao extends DAOImpl<ApplyRecord, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply, Integer> {

    /**
     * Create a new ApplyDao without any configuration
     */
    public ApplyDao() {
        super(Apply.APPLY, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply.class);
    }

    /**
     * Create a new ApplyDao with an attached configuration
     */
    public ApplyDao(Configuration configuration) {
        super(Apply.APPLY, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchById(Integer... values) {
        return fetch(Apply.APPLY.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply fetchOneById(Integer value) {
        return fetchOne(Apply.APPLY.ID, value);
    }

    /**
     * Fetch records that have <code>shareId IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchByShareid(Integer... values) {
        return fetch(Apply.APPLY.SHAREID, values);
    }

    /**
     * Fetch records that have <code>campaignId IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchByCampaignid(Integer... values) {
        return fetch(Apply.APPLY.CAMPAIGNID, values);
    }

    /**
     * Fetch records that have <code>userId IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchByUserid(Integer... values) {
        return fetch(Apply.APPLY.USERID, values);
    }

    /**
     * Fetch records that have <code>memo IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchByMemo(String... values) {
        return fetch(Apply.APPLY.MEMO, values);
    }

    /**
     * Fetch records that have <code>createTime IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchByCreatetime(LocalDateTime... values) {
        return fetch(Apply.APPLY.CREATETIME, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Apply> fetchByStatus(Integer... values) {
        return fetch(Apply.APPLY.STATUS, values);
    }
}
