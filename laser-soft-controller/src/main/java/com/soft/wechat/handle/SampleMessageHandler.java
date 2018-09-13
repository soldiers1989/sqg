package com.soft.wechat.handle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.soft.wechat.model.GroupMessage;
import com.soft.wechat.model.UserMessage;

/**
 * 一个默认的消息处理实现
 *
 * @author zjx
 */
public class SampleMessageHandler implements MessageHandle {

    /**
     * 保存微信消息
     *
     * @param msg
     */
    @Override
    public void wxSync(JsonObject msg) {

    }

    public void userMessage(UserMessage userMessage) {

        if (null == userMessage || userMessage.isEmpty()) {
            return;
        }
        String text = userMessage.getText();
        JsonObject raw_msg = userMessage.getRawMsg();
        String toUid = raw_msg.get("FromUserName").getAsString();
        // 撤回消息
        if ("test_revoke".equals(text)) {
            userMessage.getWechatApi().wxSendMessage("这条消息将被撤回", toUid);
        } else if ("reply".equals(text)) {
            userMessage.sendText("自动回复", toUid);
        } else {
            String replayMsg = "接收到：" + text;
            userMessage.sendText(replayMsg, toUid);
        }
    }

    public void groupMessage(GroupMessage groupMessage) {

        groupMessage.sendText("自动回复", groupMessage.getGroupId());
    }

    @Override
    public void groupMemberChange(String groupId, JsonArray memberList) {

    }

    @Override
    public void groupListChange(String groupId, JsonArray memberList) {

    }

}
