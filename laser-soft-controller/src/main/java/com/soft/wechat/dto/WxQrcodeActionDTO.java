package com.soft.wechat.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRawValue;

/**
 * describe:
 *
 */
public class WxQrcodeActionDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5830852954689730214L;

    @JsonProperty("expire_seconds")
    private Integer expireSeconds;

    @JsonProperty("action_name")
    private String actionName;

    @JsonProperty("action_info")
    @JsonRawValue
    private String actionInfo;

    @Override
    public String toString() {

        return "WxQrcodeActionDTO{" + "expireSeconds=" + expireSeconds + ", actionName='" + actionName + '\'' + ", actionInfo='"
                        + actionInfo + '\'' + '}';
    }

    public Integer getExpireSeconds() {

        return expireSeconds;
    }

    public void setExpireSeconds(Integer expireSeconds) {

        this.expireSeconds = expireSeconds;
    }

    public String getActionName() {

        return actionName;
    }

    public void setActionName(String actionName) {

        this.actionName = actionName;
    }

    public String getActionInfo() {

        return actionInfo;
    }

    public void setActionInfo(String actionInfo) {

        this.actionInfo = actionInfo;
    }
}
