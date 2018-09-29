package com.soft.wechat.model;

public class ImageMessage extends BaseWechatMessage {

    /**
     * 图片消息内容
     */
    private Image Image;

    public Image getImage() {

        return Image;
    }

    public void setImage(Image image) {

        Image = image;
    }

}
