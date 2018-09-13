package com.soft.wechat.enums;


/**
 * msgType为Event（事件推送消息）的枚举类型描述
 * @author ron
 * 
 */
public enum EventEnum {

	EVENT_SUBSCRIBE("EV00","subscribe","未关注时，进行关注后的事件推送"),
	EVENT_SCAN("EV01","SCAN","用户已关注时的事件推送"),
	EVENT_LOCATION("EV02","LOCATION","上报地理位置事件"),
	EVENT_CLICK("EV03","CLICK","点击菜单拉取消息时的事件推送"),
	EVENT_VIEW("EV04","VIEW","点击菜单跳转链接时的事件推送");
	
	EventEnum(String key, String code, String remark){
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
