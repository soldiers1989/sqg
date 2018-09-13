package com.soft.wechat.model;

import java.io.Serializable;

/**
 * 微信登录session
 * 
 * @author zjx
 * 
 */
public class Session implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8456342811664905817L;

    private String uuid;

    private String skey;

    private String sid;

    private String uin;

    private String passTicket;

    public String getUuid() {

        return uuid;
    }

    public void setUuid(String uuid) {

        this.uuid = uuid;
    }

    public String getSkey() {

        return skey;
    }

    public void setSkey(String skey) {

        this.skey = skey;
    }

    public String getSid() {

        return sid;
    }

    public void setSid(String sid) {

        this.sid = sid;
    }

    public String getUin() {

        return uin;
    }

    public void setUin(String uin) {

        this.uin = uin;
    }

    public String getPassTicket() {

        return passTicket;
    }

    public void setPassTicket(String passTicket) {

        this.passTicket = passTicket;
    }

    @Override
    public String toString() {

        return "Session(" + "uuid='" + uuid + '\'' + ", skey='" + skey + '\'' + ", sid='" + sid + '\'' + ", uin='" + uin + '\''
                        + ", passTicket='" + passTicket + '\'' + ')';
    }
}
