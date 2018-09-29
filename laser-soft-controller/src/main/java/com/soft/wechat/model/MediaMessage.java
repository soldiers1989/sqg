package com.soft.wechat.model;

public class MediaMessage extends BaseWechatMessage {

    /**
     * 图片消息内容
     */
    private String MediaId;

    public String getMediaId() {

        return MediaId;
    }

    public void setMediaId(String mediaId) {

        MediaId = mediaId;
    }

}
