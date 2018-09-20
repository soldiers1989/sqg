/*
 * yqbsoft
 * Copyright (c) 2014 All Rights Reserved.
 */
package com.soft.wechat.domain;


public class WeChartUserInfoBean {
	 
	/**
	 * 用户的唯一标识
	 */
    private String openid;
	/**
	 * 用户昵称
	 */
    private String nickname;
	/**
	 * 	用户的性别，值为1时是男性，值为2时是女性，值为0时是未知
	 */
    private String sex;
	/**
	 * 	用户个人资料填写的省份
	 */
    private String province;
	/**
	 * 普通用户个人资料填写的城市
	 */
    private String city;
	/**
	 * 	国家，如中国为CN
	 */
    private String country;
	/**
	 * 用户头像
	 */
    private String headimgurl;
    
    /**
	 * 
	 */
    private String unionid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	
}
