package com.soft.tbk.domain;

import com.soft.tbk.model.TbkUser;

public class UserSession extends TbkUser {

    private String sex;

    private String tokenKey;

    public String getTokenKey() {

        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {

        this.tokenKey = tokenKey;
    }

    public String getSex() {

        return sex;
    }

    public void setSex(String sex) {

        this.sex = sex;
    }

}