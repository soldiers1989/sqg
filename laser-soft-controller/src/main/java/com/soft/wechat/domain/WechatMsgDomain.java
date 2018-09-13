package com.soft.wechat.domain;

public class WechatMsgDomain {

    // 发送方帐号（一个OpenID）
    private String fromUserName;

    // 开发者微信号
    private String toUserName;

    // 消息类型
    private String msgType;

    // 消息id
    private String msgId;

    //事件类型
    private String event;

    // 消息内容
    private String content;

    public String getFromUserName() {

        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {

        this.fromUserName = fromUserName;
    }

    public String getToUserName() {

        return toUserName;
    }

    public void setToUserName(String toUserName) {

        this.toUserName = toUserName;
    }

    public String getMsgType() {

        return msgType;
    }

    public void setMsgType(String msgType) {

        this.msgType = msgType;
    }

    public String getEvent() {

        return event;
    }

    public void setEvent(String event) {

        this.event = event;
    }

    public String getContent() {

        return content;
    }

    public void setContent(String content) {

        this.content = content;
    }

    public String getMsgId() {

        return msgId;
    }

    public void setMsgId(String msgId) {

        this.msgId = msgId;
    }

}
