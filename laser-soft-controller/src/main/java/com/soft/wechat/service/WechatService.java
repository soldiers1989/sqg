package com.soft.wechat.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.util.StringUtils;
import com.soft.wechat.domain.WechatMsgDomain;
import com.soft.wechat.enums.EventEnum;
import com.soft.wechat.enums.MessageTypeEnum;
import com.soft.wechat.model.TextMessage;
import com.soft.wechat.robot.TulingRobot;
import com.soft.wechat.util.Coupon;
import com.soft.wechat.util.MapUtil;
import com.soft.wechat.util.WechatMessageUtil;

@Service
public class WechatService {

    @Autowired
    private BusinessService businessService;

    public static final String SYS_CODE = "WechatService.";

    private static Logger logger = LoggerFactory.getLogger(WechatService.class);

    @SuppressWarnings("unused")
    public String processRequest(HttpServletRequest request, String tenantCode) {

        Map<String, String> map = WechatMessageUtil.xmlToMap(request);
        if (MapUtil.isNotEmpty(map)) {
            logger.info("XML原文 ——>" + map.get("sourceText"));
        }
        // 发送方帐号（一个OpenID）
        String fromUserName = map.get("FromUserName");
        // 开发者微信号
        String toUserName = map.get("ToUserName");
        // 消息类型
        String msgType = map.get("MsgType");
        //事件类型
        String event = map.get("Event");
        // 消息内容
        String content = map.get("Content");
        // 消息id，64位整型
        String msgId = map.get("MsgId");

        //获取当前发消息的昵称
        String userNickname = "";
        if (StringUtils.isEmpty(content))
            content = "";
        // 默认回复一个"success"
        String responseMessage = "success";
        String newcontent = "";
        WechatMsgDomain wechatMsgDomain = new WechatMsgDomain();
        wechatMsgDomain.setEvent(event);
        wechatMsgDomain.setContent(content);
        wechatMsgDomain.setFromUserName(fromUserName);
        wechatMsgDomain.setMsgType(msgType);
        wechatMsgDomain.setToUserName(toUserName);
        wechatMsgDomain.setMsgId(msgId);
        // 对消息进行处理
        if (MessageTypeEnum.MESSAGE_TEXT.getCode().equals(msgType)) {
            // 文本
            // 保存接收消息
            //businessCon.saveImsg(MessageTypeEnum.MESSAGE_TEXT.getKey(), fromUserName, fromUserName, toUserName, toUserName, "微信公众号",content, tenantCode);
            // 获取微信关键字的处理配置
            newcontent = content + "&" + fromUserName + "&" + toUserName + "&" + userNickname;

        } else if (MessageTypeEnum.MESSAGE_EVENT.getCode().equals(msgType)) {//事件推送消息

            logger.info("获取到的XML参数：" + map.toString());
            String eventKey = map.get("EventKey");
            //businessCon.saveImsg(MessageTypeEnum.MESSAGE_EVENT.getKey(), fromUserName, fromUserName, toUserName, toUserName, "微信公众号","内容为：" + event + "|" + eventKey, tenantCode);
            logger.info("事件推送消息");
            if (EventEnum.EVENT_SUBSCRIBE.getCode().equals(event)) {//扫码[未关注]
                String s = "qrscene_";
                if (eventKey.contains(s)) {
                    content = eventKey.replace(s, "");
                }
            } else if (EventEnum.EVENT_SCAN.getCode().equals(event)) {//扫码[已关注]
                content = eventKey;
            } else {

            }
            newcontent = content + "#" + fromUserName;
            msgType = "text";
        }

        String returnContent = Coupon.getReturnContent(content);
        if (StringUtils.isEmpty(returnContent)) {
            returnContent = new TulingRobot(tenantCode).getResult(content);
        }
        if (StringUtils.isEmpty(returnContent)) {
            return responseMessage;
        }
        TextMessage textMessage = new TextMessage();
        textMessage.setMsgType(msgType);
        textMessage.setToUserName(fromUserName);
        textMessage.setFromUserName(toUserName);
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setContent(returnContent);
        responseMessage = WechatMessageUtil.textMessageToXml(textMessage);

        return responseMessage;
    }

    @SuppressWarnings("unused")
    private void outPutStream(HttpServletResponse response, String str) {

        if (StringUtils.isEmpty(str))
            return;
        try {
            String contextType = "text/html;charset=UTF-8";
            response.setHeader("Content-type", contextType);
            response.getOutputStream().write("elevator".getBytes());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
