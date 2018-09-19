package com.soft.wechat.domain;

import java.util.List;

public class WechatMenuDomain {

    //一级菜单数组，个数应为1~3个
    List<WechatMenuDomain> sub_button;// 二级菜单数组，个数应为1~5个

    String type;// 菜单的响应动作类型，view表示网页类型，click表示点击类型，miniprogram表示小程序类型

    String name;//   菜单标题，不超过16个字节，子菜单不超过60个字节

    String key;//    菜单KEY值，用于消息接口推送，不超过128字节

    String url;//view、miniprogram类型必须    网页 链接，用户点击菜单可打开链接，不超过1024字节。 type为miniprogram时，不支持小程序的老版本客户端将打开本url。

    String media_id;//   media_id类型和view_limited类型必须 调用新增永久素材接口返回的合法media_id

    String appid;//miniprogram类型必须 小程序的appid（仅认证公众号可配置）

    String pagepath;// miniprogram类型必须 小程序的页面路径

    public List<WechatMenuDomain> getSub_button() {

        return sub_button;
    }

    public void setSub_button(List<WechatMenuDomain> sub_button) {

        this.sub_button = sub_button;
    }

    public String getType() {

        return type;
    }

    public void setType(String type) {

        this.type = type;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {

        this.key = key;
    }

    public String getUrl() {

        return url;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    public String getMedia_id() {

        return media_id;
    }

    public void setMedia_id(String media_id) {

        this.media_id = media_id;
    }

    public String getAppid() {

        return appid;
    }

    public void setAppid(String appid) {

        this.appid = appid;
    }

    public String getPagepath() {

        return pagepath;
    }

    public void setPagepath(String pagepath) {

        this.pagepath = pagepath;
    }
}
