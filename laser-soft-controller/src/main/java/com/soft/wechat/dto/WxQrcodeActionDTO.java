package com.soft.wechat.dto;

import java.io.Serializable;

/**
 * describe:
 *
 */
public class WxQrcodeActionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5830852954689730214L;

    private Integer expire_seconds;

    private String action_name;

    private String action_info;

    @Override
    public String toString() {

        return "{\"action_info\": " + action_info + ",\"expire_seconds\": " + expire_seconds + ", \"action_name\": \"" + action_name
                        + "\" }";
    }

    public Integer getExpire_seconds() {

        return expire_seconds;
    }

    public void setExpire_seconds(Integer expire_seconds) {

        this.expire_seconds = expire_seconds;
    }

    public String getAction_name() {

        return action_name;
    }

    public void setAction_name(String action_name) {

        this.action_name = action_name;
    }

    public String getAction_info() {

        return action_info;
    }

    public void setAction_info(String action_info) {

        this.action_info = action_info;
    }
}
