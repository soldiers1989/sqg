package com.soft.wechat.domain;

import java.math.BigDecimal;

public class WechatMsgTemplateDomain {

    // 微信发送对象openId
    private String userOpenId;

    // 昵称
    private String userNikeName;

    // 交易id：显示用
    private String tradeId;

    // 订单id：查询详情用
    private Long orderId;

    // 商品title
    private String itemTitle;

    // 商品价格
    private BigDecimal itemPrice;

    // 预计佣金收益
    private BigDecimal commission;

    public String getUserOpenId() {

        return userOpenId;
    }

    public void setUserOpenId(String userOpenId) {

        this.userOpenId = userOpenId;
    }

    public String getUserNikeName() {

        return userNikeName;
    }

    public void setUserNikeName(String userNikeName) {

        this.userNikeName = userNikeName;
    }

    public String getTradeId() {

        return tradeId;
    }

    public void setTradeId(String tradeId) {

        this.tradeId = tradeId;
    }

    public Long getOrderId() {

        return orderId;
    }

    public void setOrderId(Long orderId) {

        this.orderId = orderId;
    }

    public String getItemTitle() {

        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {

        this.itemTitle = itemTitle;
    }

    public BigDecimal getItemPrice() {

        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {

        this.itemPrice = itemPrice;
    }

    public BigDecimal getCommission() {

        return commission;
    }

    public void setCommission(BigDecimal commission) {

        this.commission = commission;
    }

}
