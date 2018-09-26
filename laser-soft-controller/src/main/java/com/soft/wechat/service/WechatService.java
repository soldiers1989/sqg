package com.soft.wechat.service;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.tbk.model.TbkUser;
import com.soft.wechat.domain.WechatMsgDomain;
import com.soft.wechat.enums.EventEnum;
import com.soft.wechat.enums.MessageTypeEnum;
import com.soft.wechat.model.TextMessage;
import com.soft.wechat.robot.TulingRobot;
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

        String returnContent = "";
        // 对消息进行处理
        if (MessageTypeEnum.MESSAGE_TEXT.getCode().equals(msgType)) {
            // 文本
            returnContent = businessService.returnTKL(content, fromUserName);

            if (StringUtils.isEmpty(returnContent)) {
                returnContent = new TulingRobot(tenantCode).getResult(content);
            }
        } else if (MessageTypeEnum.MESSAGE_EVENT.getCode().equals(msgType)) {//事件推送消息

            String eventKey = map.get("EventKey");
            if (EventEnum.EVENT_SUBSCRIBE.getCode().equals(event)) {//扫码/非扫码[未关注]
                eventKey = eventKey.replace("qrscene_", "");
                TbkUser tbkUser = new TbkUser();
                tbkUser.setUserOpenid(fromUserName);
                if (StringUtils.isNotBlank(eventKey)) {
                    // 参数
                    tbkUser.setParentId(Integer.parseInt(eventKey));
                }
                businessService.executorUser(tbkUser);
            } else if (EventEnum.EVENT_UNSUBSCRIBE.getCode().equals(event)) {//取消关注
            } else if (EventEnum.EVENT_CLICK.getCode().equals(event)) {//点击菜单拉取消息时的事件推送
                if ("share".equals(eventKey)) {
                    // 分享生成二维码
                    //returnContent = businessService.generateWxQrCode(fromUserName);
                }
            }
        }

        // 如果是淘口令信息

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
