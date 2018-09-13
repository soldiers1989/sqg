package com.soft.wechat.handle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 一个默认的消息处理实现
 * 
 * @author zjx
 * 
 */
public interface MessageHandle {

    /**
     * 保存微信消息
     * 
     * @param msg
     */
    void wxSync(JsonObject msg);

    void groupMemberChange(String groupId, JsonArray memberList);

    void groupListChange(String groupId, JsonArray memberList);

}
