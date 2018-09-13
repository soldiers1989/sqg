package com.soft.wechat.handle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 一个默认的消息处理实现
 * 
 * @author zjx
 * 
 */
public abstract class AbstractMessageHandler implements MessageHandle {

    @Override
    public void wxSync(JsonObject msg) {

    }

    @Override
    public void groupListChange(String groupId, JsonArray memberList) {

    }

    @Override
    public void groupMemberChange(String groupId, JsonArray memberList) {

    }
}
