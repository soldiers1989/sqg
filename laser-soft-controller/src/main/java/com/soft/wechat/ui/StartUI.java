package com.soft.wechat.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.soft.wechat.api.WechatApi;
import com.soft.wechat.model.Const;
import com.soft.wechat.model.Environment;
import com.soft.wechat.model.GroupMessage;
import com.soft.wechat.model.UserMessage;
import com.soft.wechat.robot.TulingRobot;
import com.soft.wechat.service.BusinessService;
import com.soft.wechat.util.Utils;

/**
 * @author zjx
 */
public class StartUI extends WechatApi {

    private static final Logger log = LoggerFactory.getLogger(StartUI.class);

    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    @SuppressWarnings("unused")
    private BusinessService businessCon;

    private String tenantCode;

    private String path;

    public String getPath() {

        return path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    public void setBusinessCon(BusinessService businessCon) {

        this.businessCon = businessCon;
    }

    public StartUI(Environment environment) {
        super(environment);
    }

    public void setTenantCode(String tenantCode) {

        this.tenantCode = tenantCode;
    }

    private void waitForLogin() {

        log.info(Const.LOG_MSG_GET_UUID);
        getUUID();
        while (true) {
            log.info(Const.LOG_MSG_GET_QRCODE);
            String path = genqrCode();
            this.setPath(path);
            log.info(Const.LOG_MSG_SCAN_QRCODE);
            if (loginTimeout <= 0)
                break;
            if (!waitforlogin(1)) {
                continue;
            }
            log.info(Const.LOG_MSG_CONFIRM_LOGIN);
            if (!waitforlogin(0)) {
                continue;
            }
            break;
        }
    }

    /**
     * 启动机器人
     */
    public void start() {

        log.info(Const.LOG_MSG_START);
        log.info(Const.LOG_MSG_TRY_INIT);
        if (webwxinit()) {
            log.info(Const.LOG_MSG_SUCCESS);
        } else {
            waitForLogin();
            if (loginTimeout <= 0) {
                log.info(Const.LOG_MSG_WAIT_LOGIN_ERR1);
                return;
            }
            log.info(Const.LOG_MSG_LOGIN);
            if (!login()) {
                log.warn("登录失败");
            }
            log.info(Const.LOG_MSG_INIT);
            if (!webwxinit()) {
                log.warn("初始化失败");
            }
        }

        log.info(Const.LOG_MSG_STATUS_NOTIFY);
        if (!openStatusNotify()) {
            log.warn("状态通知打开失败");
        }
        log.info(Const.LOG_MSG_GET_CONTACT);
        if (!getContact()) {
            log.warn("获取联系人失败");
        }
        log.info(Const.LOG_MSG_CONTACT_COUNT, memberCount, memberList.size());
        log.info(Const.LOG_MSG_OTHER_CONTACT_COUNT, groupList.size(), contactList.size(), specialUsersList.size(), publicUsersList.size());

        if (groupList.size() > 0) {
            executorService.execute(new Runnable() {

                @Override
                public void run() {

                    log.info(Const.LOG_MSG_GET_GROUP_MEMBER);
                    StartUI.super.fetchGroupContacts();
                }
            });
        }
        log.info(Const.LOG_MSG_SNAPSHOT);
        super.snapshot();
        this.listen();
    }

    private void listen() {

        while (true) {
            try {
                boolean flag = false;
                // retcode, selector
                int[] checkResponse = synccheck();
                int retcode = checkResponse[0];
                int selector = checkResponse[1];
                log.debug("retcode: {}, selector: {}", retcode, selector);
                switch (retcode) {
                    case 1100:
                        log.warn(Const.LOG_MSG_LOGOUT);
                        flag = true;
                        break;
                    case 1101:
                        log.warn(Const.LOG_MSG_LOGIN_OTHERWHERE);
                        flag = true;
                        break;
                    case 1102:
                        log.warn(Const.LOG_MSG_QUIT_ON_PHONE);
                        flag = true;
                        break;
                    case 0:
                        this.handle(selector);
                        break;
                    default:
                        log.debug("wxSync: {}\n", wxSync().toString());
                        break;
                }
                if (flag)
                    break;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除好友,删除群
     * 
     * @param dic
     */
    private void handle_del(JsonObject dic) {

        int delContactCount = dic.get("DelContactCount").getAsInt();
        if (delContactCount > 0) {
            JsonArray delContactList = dic.getAsJsonArray("DelContactList");// 删除好友
            for (JsonElement delElement : delContactList) {
                JsonObject m = delElement.getAsJsonObject();
                if (m.get("UserName").getAsString().startsWith("@@")) {
                    JsonArray GroupList = new JsonArray();
                    GroupList.addAll(groupList);
                    String u_id = m.get("UserName").getAsString();
                    for (JsonElement oldElement : groupList) {
                        JsonObject n = oldElement.getAsJsonObject();
                        String n_id = n.get("UserName").getAsString();
                        if (u_id.equals(n_id)) {
                            GroupList.remove(n);
                            groupMemeberList.remove(u_id);
                            this.groupList = GroupList;
                            break;
                        }
                    }
                } else if (m.get("UserName").getAsString().startsWith("@")) {
                    JsonArray MemberList = new JsonArray();
                    MemberList.addAll(memberList);
                    String u_id = m.get("UserName").getAsString();
                    for (JsonElement oldElement : memberList) {
                        JsonObject n = oldElement.getAsJsonObject();
                        String n_id = n.get("UserName").getAsString();
                        if (u_id.equals(n_id)) {
                            MemberList.remove(n);
                            break;
                        }
                    }
                    this.memberList = MemberList;
                }
            }
        }
    }

    /**
     * 添加好友，添加群，添加群成员，修改群名称
     * 
     * @param dic
     */
    private void handle_mod(JsonObject dic) {

        int modContactCount = dic.get("ModContactCount").getAsInt();
        if (modContactCount > 0) {
            JsonArray modContactList = dic.getAsJsonArray("ModContactList");// 修改群名称,添加群成员,删除群成员，添加好友
            for (JsonElement element : modContactList) {
                JsonObject m = element.getAsJsonObject();
                if (m.get("UserName").getAsString().startsWith("@@")) {
                    boolean in_list = false;
                    String g_id = m.get("UserName").getAsString();
                    for (JsonElement ge : groupList) {
                        JsonObject group = ge.getAsJsonObject();
                        if (g_id.equals(group.get("UserName").getAsString())) {
                            in_list = true;
                            group.addProperty("MemberCount", m.get("MemberCount").getAsInt());
                            group.addProperty("NickName", m.get("NickName").getAsString());
                            // 群成员重新放入
                            this.groupMemeberList.put(g_id, m.get("MemberList").getAsJsonArray());
                            break;
                        }
                    }
                    if (!in_list) {
                        this.groupList.add(m);
                        this.groupMemeberList.put(g_id, m.get("MemberList").getAsJsonArray());
                    }
                } else if (m.get("UserName").getAsString().startsWith("@")) {
                    boolean in_list = false;
                    for (JsonElement ue : memberList) {
                        JsonObject u = ue.getAsJsonObject();
                        String u_id = m.get("UserName").getAsString();
                        if (u_id.equals(u.get("UserName").getAsString())) {
                            u = m;
                            in_list = true;
                            break;
                        }
                    }
                    if (!in_list) {
                        this.memberList.add(m);
                    }
                }
            }
        }
        //		JsonArray modChatRoomMemberList = dic.getAsJsonArray("ModChatRoomMemberList");
    }

    public void handle_msg(JsonObject dic) {

        log.debug("handle message");
        int addMsgCount = dic.get("AddMsgCount").getAsInt();
        if (addMsgCount == 0) {
            return;
        }
        // 消息变动
        log.debug(Const.LOG_MSG_NEW_MSG, addMsgCount);
        JsonArray msgs = dic.getAsJsonArray("AddMsgList");
        for (JsonElement element : msgs) {
            JsonObject msg = element.getAsJsonObject();

            String msgType = msg.get("MsgType").getAsString();
            String content = msg.get("Content").getAsString().replace("&lt;", "<").replace("&gt;", ">");
            UserMessage userMessage = new UserMessage(this);
            userMessage.setRawMsg(msg);

            // 文本groupMessage
            if (conf.get("MSGTYPE_TEXT").equals(msgType)) {
                // 地理位置消息
                if (content.contains("pictype=location")) {
                    String location = content.split("<br/>")[1];
                    userMessage.setLocation(location);
                    userMessage.setLog(String.format(Const.LOG_MSG_LOCATION, location));
                } else {
                    // 普通文本
                    String text = null;
                    if (content.contains(":<br/>")) {
                        text = content.split(":<br/>")[1];
                    } else {
                        text = content;
                    }
                    userMessage.setText(text);
                    userMessage.setLog(text.replace("<br/>", "\n"));
                }
            } else if (conf.get("MSGTYPE_STATUSNOTIFY").equals(msgType)) {
                log.info(Const.LOG_MSG_NOTIFY_PHONE);
                break;
            } else {
                break;
            }

            this.show_msg(userMessage);

            String question = userMessage.getText();
            boolean isGroupMsg = (msg.get("FromUserName").getAsString() + msg.get("ToUserName").getAsString()).contains("@@");
            String result = null;
            String toUid = "";// 接收方
            if (isGroupMsg) {
                // 群聊
                GroupMessage groupMessage = make_group_msg(userMessage);
                toUid = groupMessage.getGroupId();
                String nickName = getNikeNameByGroupId(toUid, question);
                result = "";//TODO 本系统机器人,有业务处理业务
                if (StringUtils.isEmpty(result)
                                && (this.user.get("NickName").equals(nickName) || question.contains("@" + this.user.get("NickName")))) {
                    result = new TulingRobot(tenantCode).getResult(question);
                }
            } else {
                String flag = "";
                if (StringUtils.isEmpty(flag)) {
                    return;// 默认个人不自动回复
                }
                JsonObject raw_msg = userMessage.getRawMsg();
                toUid = raw_msg.get("FromUserName").getAsString();
                result = new TulingRobot(tenantCode).getResult(question);
            }
            if (StringUtils.isEmpty(result) || StringUtils.isEmpty(toUid)) {
                return;
            }
            //消息保存
            //businessCon.saveImsg(MessageTypeEnum.MESSAGE_TEXT.getKey(), toUid, toUid, user.get("UserName").toString(), user.get("NickName").toString(), "微信聊天", content, tenantCode);
            // 消息发送
            userMessage.sendText(result, toUid);
        }
    }

    /**
     * 根据群昵称查询本身昵称
     * 
     * @param groupId
     * @param question
     * @return
     */
    private String getNikeNameByGroupId(String groupId, String question) {

        JsonArray memebers = groupMemeberList.get(groupId);
        if (memebers == null)
            return null;
        for (JsonElement element : memebers) {
            JsonObject member = element.getAsJsonObject();
            String d = Utils.emptyOr(member.get("DisplayName").getAsString(), "");
            if (!Utils.isBlank(d) && question.contains(d)) {
                String n = Utils.emptyOr(member.get("NickName").getAsString(), "");
                return n;
            }
        }
        return null;
    }

    private GroupMessage make_group_msg(UserMessage userMessage) {

        log.debug("make group message");
        GroupMessage groupMessage = new GroupMessage(this);
        groupMessage.setRawMsg(userMessage.getRawMsg());
        groupMessage.setMsgId(userMessage.getRawMsg().get("MsgId").getAsString());
        groupMessage.setFromUserName(userMessage.getRawMsg().get("FromUserName").getAsString());
        groupMessage.setToUserName(userMessage.getRawMsg().get("ToUserName").getAsString());
        groupMessage.setMsgType(userMessage.getRawMsg().get("MsgType").getAsString());
        groupMessage.setText(userMessage.getText());

        String content = userMessage.getRawMsg().get("Content").getAsString().replace("&lt;", "<").replace("&gt;", ">");

        Map<String, String> group = null, src = null;

        if (groupMessage.getFromUserName().startsWith("@@")) {
            // 接收到来自群的消息
            String g_id = groupMessage.getFromUserName();
            groupMessage.setGroupId(g_id);
            group = this.getGroupById(g_id);
            if (content.contains(":<br/>")) {
                String u_id = content.split(":<br/>")[0];
                src = getGroupUserById(u_id, g_id);
            }
        } else if (groupMessage.getToUserName().startsWith("@@")) {
            // 自己发给群的消息
            String g_id = groupMessage.getToUserName();
            groupMessage.setGroupId(g_id);
            String u_id = groupMessage.getFromUserName();
            src = this.getGroupUserById(u_id, g_id);
            group = this.getGroupById(g_id);
        }

        if (null != src) {
            groupMessage.setUser_attrstatus(src.get("AttrStatus"));
            groupMessage.setUser_display_name(src.get("DisplayName"));
            groupMessage.setUser_nickname(src.get("NickName"));
        }
        if (null != group) {
            groupMessage.setGroup_count(group.get("MemberCount"));
            groupMessage.setGroup_owner_uin(group.get("OwnerUin"));
            groupMessage.setGroup_name(group.get("ShowName"));
        }
        groupMessage.setTimestamp(userMessage.getRawMsg().get("CreateTime").getAsString());

        return groupMessage;
    }

    private void show_msg(UserMessage userMessage) {

        Map<String, Object> src = null;
        Map<String, Object> dst = null;
        Map<String, String> group = null;
        JsonObject msg = userMessage.getRawMsg();

        String content = msg.get("Content").getAsString();
        content = content.replace("&lt;", "<").replace("&gt;", ">");

        String msg_id = msg.get("MsgId").getAsString();

        // 接收到来自群的消息
        if (msg.get("FromUserName").getAsString().substring(2).equals("@@")) {
            String groupId = msg.get("FromUserName").getAsString();
            group = this.getGroupById(groupId);
            if (content.contains(":<br/>")) {
                String u_id = content.split(":<br/>")[0];
                src = new HashMap<String, Object>(this.getGroupUserById(u_id, groupId));
                dst = Utils.createMap("ShowName", "GROUP");
            } else {
                String u_id = msg.get("ToUserName").getAsString();
                src = new HashMap<String, Object>(Utils.createMap("ShowName", "SYSTEM"));
                dst = new HashMap<String, Object>(getGroupUserById(u_id, groupId));
            }
        } else {
            // 非群聊消息
            src = new HashMap<String, Object>(this.getUserById(msg.get("FromUserName").getAsString()));
            dst = new HashMap<String, Object>(this.getUserById(msg.get("ToUserName").getAsString()));
        }
        if (null != group) {
            log.info("{} |{}| {} -> {}: {}\n", msg_id, group.get("ShowName"), dst.get("ShowName"), userMessage.getLog());
        } else {
            log.info("{} {} -> {}: {}\n", msg_id, src.get("ShowName"), dst.get("ShowName"), userMessage.getLog());
        }
    }

    private void handle(int selector) {

        JsonObject dic = wxSync();
        if (null == dic)
            return;
        switch (selector) {
            case 7:
                // 关注公众号
                break;
            case 5:
                // 取消关注公众号
                break;
            default:
                handle_mod(dic);
                handle_del(dic);
                handle_msg(dic);
                break;
        }
    }

}
