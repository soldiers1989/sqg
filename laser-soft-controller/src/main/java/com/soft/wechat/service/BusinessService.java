package com.soft.wechat.service;

import org.springframework.stereotype.Service;

import com.soft.wechat.domain.WechatMsgDomain;
import com.soft.wechat.util.Coupon;

@Service
public class BusinessService {

    public String getReturnContent(WechatMsgDomain wechatMsgDomain) {

        String content = wechatMsgDomain.getContent();
        String returnContent = Coupon.getReturnContent(content);
        saveMsg(wechatMsgDomain, returnContent);
        return returnContent;
    }

    private void saveMsg(WechatMsgDomain wechatMsgDomain, String returnContent) {

        // TODO 保存入库
    }
}
