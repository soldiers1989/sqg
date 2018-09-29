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
import com.soft.wechat.model.MediaMessage;
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
            // 如果是淘口令信息
            if (StringUtils.isEmpty(returnContent)) {
                return responseMessage;
            }
            responseMessage = makeTextMessage(wechatMsgDomain, returnContent);

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
                // 初始化关注时的回复
                returnContent = "欢迎加入桃桃，让你省钱又赚钱。无需注册，完成首次查询发送后，就是终身会员了。开始吧GO\n" + "1、淘宝上随便找个宝贝，可以是买过的，或是购物车里的宝贝！分享至桃桃公众号\n"
                                + "2、发到桃桃，就能检查出优惠券（特别是隐藏劵），佣金也是你自己的\n" + "3、操作流程图请点击查看【使用指南】\n";
                responseMessage = makeTextMessage(wechatMsgDomain, returnContent);
            } else if (EventEnum.EVENT_UNSUBSCRIBE.getCode().equals(event)) {//取消关注
            } else if (EventEnum.EVENT_CLICK.getCode().equals(event)) {//点击菜单拉取消息时的事件推送
                if ("share".equals(eventKey)) {
                    // 分享生成二维码
                    String mediaId = businessService.getMediaIdByShare(fromUserName);
                    responseMessage = makeMediaMessage(wechatMsgDomain, mediaId);
                } else if ("waiting".equals(eventKey)) {
                    responseMessage = makeTextMessage(wechatMsgDomain, "功能升级中，敬请期待...");
                } else if (eventKey.startsWith("mediaId-")) {
                    // 回复永久图片素材消息
                    String mediaId = eventKey.split("-")[1];
                    responseMessage = makeMediaMessage(wechatMsgDomain, mediaId);
                }
            }
        }

        return responseMessage;
    }

    /**
     * 图片消息
     * 
     * @param wechatMsgDomain
     * @param content
     * @return
     */
    private String makeMediaMessage(WechatMsgDomain wechatMsgDomain, String mediaId) {

        MediaMessage mediaMessage = new MediaMessage();
        mediaMessage.setMsgType(MessageTypeEnum.MESSAtGE_IMAGE.getCode());
        mediaMessage.setToUserName(wechatMsgDomain.getFromUserName());
        mediaMessage.setFromUserName(wechatMsgDomain.getToUserName());
        mediaMessage.setCreateTime(System.currentTimeMillis());
        mediaMessage.setMediaId(mediaId);
        return WechatMessageUtil.textMessageToXml(mediaMessage);
    }

    /**
     * 文本消息
     * 
     * @param wechatMsgDomain
     * @param content
     * @return
     */
    private String makeTextMessage(WechatMsgDomain wechatMsgDomain, String content) {

        TextMessage textMessage = new TextMessage();
        textMessage.setMsgType(MessageTypeEnum.MESSAGE_TEXT.getCode());
        textMessage.setToUserName(wechatMsgDomain.getFromUserName());
        textMessage.setFromUserName(wechatMsgDomain.getToUserName());
        textMessage.setCreateTime(System.currentTimeMillis());
        textMessage.setContent(content);
        return WechatMessageUtil.textMessageToXml(textMessage);
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
