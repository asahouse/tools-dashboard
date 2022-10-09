/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.toolsdashboardaatool.jooq.aatool.tables.pojos;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.annotation.Generated;


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
public class AreaActivityRegistrationForm implements Serializable {

    private static final long serialVersionUID = -1656009881;

    private Integer       id;
    private Integer       batchid;
    private Integer       userid;
    private String        name;
    private String        ada;
    private LocalDateTime createtime;

    public AreaActivityRegistrationForm() {}

    public AreaActivityRegistrationForm(AreaActivityRegistrationForm value) {
        this.id = value.id;
        this.batchid = value.batchid;
        this.userid = value.userid;
        this.name = value.name;
        this.ada = value.ada;
        this.createtime = value.createtime;
    }

    public AreaActivityRegistrationForm(
        Integer       id,
        Integer       batchid,
        Integer       userid,
        String        name,
        String        ada,
        LocalDateTime createtime
    ) {
        this.id = id;
        this.batchid = batchid;
        this.userid = userid;
        this.name = name;
        this.ada = ada;
        this.createtime = createtime;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBatchid() {
        return this.batchid;
    }

    public void setBatchid(Integer batchid) {
        this.batchid = batchid;
    }

    public Integer getUserid() {
        return this.userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAda() {
        return this.ada;
    }

    public void setAda(String ada) {
        this.ada = ada;
    }

    public LocalDateTime getCreatetime() {
        return this.createtime;
    }

    public void setCreatetime(LocalDateTime createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("AreaActivityRegistrationForm (");

        sb.append(id);
        sb.append(", ").append(batchid);
        sb.append(", ").append(userid);
        sb.append(", ").append(name);
        sb.append(", ").append(ada);
        sb.append(", ").append(createtime);

        sb.append(")");
        return sb.toString();
    }
}