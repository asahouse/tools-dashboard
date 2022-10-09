/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.daos;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Generated;

import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.Template;
import org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.records.TemplateRecord;
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
public class TemplateDao extends DAOImpl<TemplateRecord, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template, Integer> {

    /**
     * Create a new TemplateDao without any configuration
     */
    public TemplateDao() {
        super(Template.TEMPLATE, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template.class);
    }

    /**
     * Create a new TemplateDao with an attached configuration
     */
    public TemplateDao(Configuration configuration) {
        super(Template.TEMPLATE, org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template.class, configuration);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Integer getId(org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template object) {
        return object.getId();
    }

    /**
     * Fetch records that have <code>id IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchById(Integer... values) {
        return fetch(Template.TEMPLATE.ID, values);
    }

    /**
     * Fetch a unique record that has <code>id = value</code>
     */
    public org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template fetchOneById(Integer value) {
        return fetchOne(Template.TEMPLATE.ID, value);
    }

    /**
     * Fetch records that have <code>name IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByName(String... values) {
        return fetch(Template.TEMPLATE.NAME, values);
    }

    /**
     * Fetch records that have <code>sequence IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchBySequence(Integer... values) {
        return fetch(Template.TEMPLATE.SEQUENCE, values);
    }

    /**
     * Fetch records that have <code>title IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByTitle(String... values) {
        return fetch(Template.TEMPLATE.TITLE, values);
    }

    /**
     * Fetch records that have <code>location IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByLocation(String... values) {
        return fetch(Template.TEMPLATE.LOCATION, values);
    }

    /**
     * Fetch records that have <code>digest IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByDigest(String... values) {
        return fetch(Template.TEMPLATE.DIGEST, values);
    }

    /**
     * Fetch records that have <code>applyDeadline IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByApplydeadline(LocalDateTime... values) {
        return fetch(Template.TEMPLATE.APPLYDEADLINE, values);
    }

    /**
     * Fetch records that have <code>startTime IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByStarttime(LocalDateTime... values) {
        return fetch(Template.TEMPLATE.STARTTIME, values);
    }

    /**
     * Fetch records that have <code>endTime IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByEndtime(LocalDateTime... values) {
        return fetch(Template.TEMPLATE.ENDTIME, values);
    }

    /**
     * Fetch records that have <code>limit IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByLimit(Integer... values) {
        return fetch(Template.TEMPLATE.LIMIT, values);
    }

    /**
     * Fetch records that have <code>coverImageKey IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByCoverimagekey(String... values) {
        return fetch(Template.TEMPLATE.COVERIMAGEKEY, values);
    }

    /**
     * Fetch records that have <code>chargeImageKey IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByChargeimagekey(String... values) {
        return fetch(Template.TEMPLATE.CHARGEIMAGEKEY, values);
    }

    /**
     * Fetch records that have <code>content IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByContent(String... values) {
        return fetch(Template.TEMPLATE.CONTENT, values);
    }

    /**
     * Fetch records that have <code>canCoverWorkshop IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByCancoverworkshop(Integer... values) {
        return fetch(Template.TEMPLATE.CANCOVERWORKSHOP, values);
    }

    /**
     * Fetch records that have <code>isCharge IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByIscharge(Integer... values) {
        return fetch(Template.TEMPLATE.ISCHARGE, values);
    }

    /**
     * Fetch records that have <code>status IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByStatus(Integer... values) {
        return fetch(Template.TEMPLATE.STATUS, values);
    }

    /**
     * Fetch records that have <code>createTime IN (values)</code>
     */
    public List<org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos.Template> fetchByCreatetime(LocalDateTime... values) {
        return fetch(Template.TEMPLATE.CREATETIME, values);
    }
}