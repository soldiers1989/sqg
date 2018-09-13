package com.soft.wechat.enums;

public enum MessageTypeEnum {
	MESSAGE_TEXT("WX00","text","文本消息"),
    MESSAtGE_IMAGE("WX01","image","图片消息"),
    MESSAGE_NEWS("WX02","news","图文消息"),
    MESSAGE_VOICE("WX03","voice","语音消息"),
    MESSAGE_VIDEO("WX04","video","视频消息"),
    MESSAGE_SHORTVIDEO("WX05","shortvideo","小视频消息"),
    MESSAGE_LOCATION("WX06","location","地理位置消息"),
    MESSAGE_LINK("WX07","link","链接消息"),
    MESSAGE_EVENT("WX08","event","事件推送消息"),
    MESSAGE_EVENT_SUBSCRIBE("WX09","subscribe","subscribe(订阅)"),
    MESSAGE_EVENT_UNSUBSCRIBE("WX10","unsubscribe","unsubscribe(取消订阅)"),
    MESSAGE_EVENT_LOCATION_UP("WX11","LOCATION","上报地理位置事件"),
    MESSAGE_EVENT_CLICK("WX12","CLICK","点击菜单拉取消息时的事件推送"),
    MESSAGE_EVENT_VIEW("WX13","VIEW","点击菜单跳转链接时的事件推送");
	MessageTypeEnum(String key, String code, String remark){
		this.key = key;
		this.code = code;
		this.remark = remark;
	}
	private String key;
	private String code;
	private String remark;
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
