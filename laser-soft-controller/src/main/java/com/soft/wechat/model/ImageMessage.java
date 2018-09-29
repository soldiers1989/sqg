package com.soft.wechat.model;

public class ImageMessage extends BaseWechatMessage {

    /**
     * 图片消息内容
     */
    private MediaMessage mediaMessage;

    public MediaMessage getMediaMessage() {

        return mediaMessage;
    }

    public void setMediaMessage(MediaMessage mediaMessage) {

        this.mediaMessage = mediaMessage;
    }

}
