/*
 * This file is generated by jOOQ.
*/
package org.codeworks.web.tools.dashboard.myspace.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;

import javax.annotation.Generated;


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
public class User implements Serializable {

    private static final long serialVersionUID = 1869281257;

    private Integer   id;
    private Integer   isabo;
    private String    openid;
    private String    nickname;
    private String    headimgurl;
    private Timestamp createtime;

    public User() {}

    public User(User value) {
        this.id = value.id;
        this.isabo = value.isabo;
        this.openid = value.openid;
        this.nickname = value.nickname;
        this.headimgurl = value.headimgurl;
        this.createtime = value.createtime;
    }

    public User(
        Integer   id,
        Integer   isabo,
        String    openid,
        String    nickname,
        String    headimgurl,
        Timestamp createtime
    ) {
        this.id = id;
        this.isabo = isabo;
        this.openid = openid;
        this.nickname = nickname;
        this.headimgurl = headimgurl;
        this.createtime = createtime;
    }

    public Integer getId() {
        return this.id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getIsabo() {
        return this.isabo;
    }

    public User setIsabo(Integer isabo) {
        this.isabo = isabo;
        return this;
    }

    public String getOpenid() {
        return this.openid;
    }

    public User setOpenid(String openid) {
        this.openid = openid;
        return this;
    }

    public String getNickname() {
        return this.nickname;
    }

    public User setNickname(String nickname) {
        this.nickname = nickname;
        return this;
    }

    public String getHeadimgurl() {
        return this.headimgurl;
    }

    public User setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
        return this;
    }

    public Timestamp getCreatetime() {
        return this.createtime;
    }

    public User setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("User (");

        sb.append(id);
        sb.append(", ").append(isabo);
        sb.append(", ").append(openid);
        sb.append(", ").append(nickname);
        sb.append(", ").append(headimgurl);
        sb.append(", ").append(createtime);

        sb.append(")");
        return sb.toString();
    }
}
