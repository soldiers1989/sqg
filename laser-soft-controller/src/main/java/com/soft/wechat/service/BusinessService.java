package com.soft.wechat.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseController;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.service.TbkCouponService;
import com.soft.tbk.service.TbkRateService;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.utils.ListUtil;

@Service
public class BusinessService extends BaseController {

    Logger logger = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    TbkCouponService tbkCouponService;

    @Autowired
    TbkUserService tbkUserService;

    @Autowired
    TbkRateService tbkRateService;

    @Autowired
    TbkCommissionService tbkCommissionService;

    @Autowired
    TbkCoreService tbkCoreService;

    @Autowired
    IWechatService wechatService;

    public void batchOrderList(List<TbkOrder> orderList) {

        if (ListUtil.isEmpty(orderList)) {
            return;
        }

        for (TbkOrder tbkOrder : orderList) {
            try {
                tbkCoreService.saveOrder(tbkOrder);
            } catch (Exception e) {
                logger.error("BusinessService.batchOrderList", e);
            }
        }

    }

    public void batchSettleOrderList(List<TbkOrder> orderList) {

        if (ListUtil.isEmpty(orderList)) {
            return;
        }

        for (TbkOrder tbkOrder : orderList) {
            try {
                tbkCoreService.settleOrder(tbkOrder);
            } catch (Exception e) {
                logger.error("BusinessService.batchOrderList", e);
            }
        }

    }

    public String returnTKL(String tkl, String openId) {

        TbkUser tbkUser = tbkCoreService.loadTbkUserInfo(openId, null);

        if (tbkUser == null) {
            logger.error(openId + "，获取用户信息失败");
        }

        TbkCoupon tbkCoupon = tbkCoreService.createTbkCoupon(tkl, tbkUser);

        return tbkCoupon.getTkl();
    }

    private ExecutorService executor = Executors.newCachedThreadPool();

    public void executorCounpon(TbkCoupon tbkCoupon) {

        executor.submit(new Runnable() {

            public void run() {

                logger.info("executorCounpon: {}", JSONArray.toJSON(tbkCoupon));
                try {
                    tbkCouponService.saveTbkCoupon(tbkCoupon);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
    }

    private ExecutorService executor1 = Executors.newCachedThreadPool();

    public void executorUser(TbkUser tbkUser) {

        executor1.submit(new Runnable() {

            public void run() {

                logger.info("executorUser: {}", JSONArray.toJSON(tbkUser));
                try {
                    tbkUserService.saveTbkUserWithOpenId(tbkUser);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
    }

    /**
     * 微信回复分享二维码,返回图片mediaId
     * 
     * @return
     */
    public String getMediaIdByShare(String openId) {

        String mediaId = "";
        try {
            String code = "";
            TbkUser tbkUser = tbkUserService.getTbkUserByOpenid(openId);
            if (tbkUser != null) {
                code = tbkUser.getId().toString();
            }
            String url = wechatService.createQrcode(code);// 获取分享二维码
            String result = wechatService.uploadMedia("image", url);// 上传临时图片
            JSONObject jsonObject = JSONObject.parseObject(result);
            mediaId = jsonObject.getString("media_id");
        } catch (Exception e) {
            logger.error("微信回复分享二维码失败", e.getMessage());
        }
        return mediaId;
    }
}
