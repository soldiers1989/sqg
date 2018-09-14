package com.soft.wechat.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.service.TbkCouponService;
import com.soft.wechat.domain.WechatMsgDomain;

@Service
public class BusinessService {

    Logger logger = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    TbkCouponService tbkCouponService;

    private void saveMsg(WechatMsgDomain wechatMsgDomain, String returnContent) {

        // TODO 保存入库
    }

    /**
     * 封装转券
     * 
     * @param object
     * @param highObject
     * @param tkl
     * @param newtkl
     */
    public void saveCoupon(JSONObject object, JSONObject highObject, String tkl, String newtkl) {

        try {
            TbkCoupon tbkCoupon = new TbkCoupon();
            if (object != null) {
                String title = object.getString("content");
                String image = object.getString("pic_url");
                String itemId = object.getString("itemId");
                tbkCoupon.setPid(object.getString("pid"));
                tbkCoupon.setItemId(StringUtils.isBlank(itemId) ? null : Long.parseLong(itemId));
                tbkCoupon.setItemTitle(title);
                tbkCoupon.setItemImage(image);
                tbkCoupon.setInputTkl(tkl);
                tbkCoupon.setTkl(newtkl);
            }
            if (highObject != null) {
                tbkCoupon.setItemPrice(new BigDecimal("0"));// 商品价格 TODO
                String max_commission_rate = highObject.getString("max_commission_rate");
                tbkCoupon.setCommission(new BigDecimal("0"));// 预计佣金 TODO
                tbkCoupon.setCouponAmout(new BigDecimal("0"));// 优惠券金额 TODO
            }
            if (tbkCoupon.getItemId() == null) {
                logger.info("商品ID为空");
                return;
            }
            tbkCouponService.saveTbkCoupon(tbkCoupon);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }
}
