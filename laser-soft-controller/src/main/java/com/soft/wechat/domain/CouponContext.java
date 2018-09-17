package com.soft.wechat.domain;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.model.TbkCoupon;
import com.taobao.api.response.TbkItemInfoGetResponse;

public class CouponContext {

    private String tkl;
    private String pid;
    private JSONObject object;
    private String itemId;
    private TbkItemInfoGetResponse itemObject;
    private TbkCoupon tbkCoupon;
    private BigDecimal userRate;

    public String getTkl() {
        return tkl;
    }

    public String getPid() {
        return pid;
    }

    public JSONObject getObject() {
        return object;
    }

    public String getItemId() {
        return itemId;
    }

    public TbkItemInfoGetResponse getItemObject() {
        return itemObject;
    }

    public TbkCoupon getTbkCoupon() {
        return tbkCoupon;
    }

    public BigDecimal getUserRate() {
        return userRate;
    }

    public void setTkl(String tkl) {
        this.tkl = tkl;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setObject(JSONObject object) {
        this.object = object;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public void setItemObject(TbkItemInfoGetResponse itemObject) {
        this.itemObject = itemObject;
    }

    public void setTbkCoupon(TbkCoupon tbkCoupon) {
        this.tbkCoupon = tbkCoupon;
    }

    public void setUserRate(BigDecimal userRate) {
        this.userRate = userRate;
    }

}
