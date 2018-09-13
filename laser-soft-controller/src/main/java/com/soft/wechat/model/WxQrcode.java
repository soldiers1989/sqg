package com.soft.wechat.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * describe:
 *
 */
public class WxQrcode {

    private String ticket;

    @JsonProperty("expire_seconds")
    private Long expireSeconds;

    private String url;

    public String getTicket() {

        return ticket;
    }

    public void setTicket(String ticket) {

        this.ticket = ticket;
    }

    public Long getExpireSeconds() {

        return expireSeconds;
    }

    public void setExpireSeconds(Long expireSeconds) {

        this.expireSeconds = expireSeconds;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }
}
