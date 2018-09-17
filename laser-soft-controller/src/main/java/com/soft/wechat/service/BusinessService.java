package com.soft.wechat.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.TbkConstants;
import com.soft.tbk.model.TbkCommission;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.service.TbkCouponService;
import com.soft.tbk.service.TbkRateService;
import com.soft.tbk.service.TbkUserService;

@Service
public class BusinessService {

    Logger logger = LoggerFactory.getLogger(BusinessService.class);

    @Autowired
    TbkCouponService tbkCouponService;

    @Autowired
    TbkUserService tbkUserService;

    @Autowired
    TbkRateService tbkRateService;

    @Autowired
    TbkCommissionService tbkCommissionService;

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

    /**
     * 封装佣金记录
     * 
     * @param amount 总佣金
     * @param userId 用户id
     */
    public void saveCommissionList(BigDecimal amount, Integer userId) {

        try {
            List<TbkCommission> list = new ArrayList<>();
            list.add(makeCommission(amount, userId, TbkConstants.RATE_LEVEL_0));// 自己的佣金
            TbkUser tbkUser = tbkUserService.getTbkUser(userId);
            if (tbkUser != null && StringUtils.isNotBlank(tbkUser.getParentIdPath())) {
                String parentIdPath = tbkUser.getParentIdPath();
                String[] userIds = parentIdPath.split(",");
                if (userIds.length > 2) {
                    logger.error("分佣级别有误，请查看用户id:" + userId);
                } else {
                    if (userIds.length == 1) {
                        // 一级佣金
                        TbkCommission tbkCommission = makeCommission(amount, Integer.parseInt(userIds[0]), TbkConstants.RATE_LEVEL_1);
                        tbkCommission.setRelationUserId(userId);
                        list.add(tbkCommission);
                    }
                    if (userIds.length == 2) {
                        // 二级佣金
                        TbkCommission tbkCommission2 = makeCommission(amount, Integer.parseInt(userIds[0]), TbkConstants.RATE_LEVEL_2);
                        tbkCommission2.setRelationUserId(userId);
                        list.add(tbkCommission2);
                        TbkCommission tbkCommission1 = makeCommission(amount, Integer.parseInt(userIds[1]), TbkConstants.RATE_LEVEL_1);
                        tbkCommission1.setRelationUserId(userId);
                        list.add(tbkCommission1);
                    }
                }
            }
            tbkCommissionService.insertBatch(list);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private TbkCommission makeCommission(BigDecimal amount, Integer userId, Integer rateLevel) {

        TbkUser tbkUser = tbkUserService.getTbkUser(userId);
        if (tbkUser == null)
            return null;
        String userLevel = tbkUser.getUserLevel();
        if (StringUtils.isBlank(userLevel)) {
            userLevel = "A";// 默认
        }
        BigDecimal rate = tbkRateService.getRateByLevel(userLevel, rateLevel);
        TbkCommission tbkCommission = new TbkCommission();
        tbkCommission.setCommissionType(rateLevel.toString());
        tbkCommission.setUserId(userId);
        tbkCommission.setCommission(amount.multiply(rate).divide(new BigDecimal(100), 2));
        return tbkCommission;
    }

    private ExecutorService executor = Executors.newCachedThreadPool();

    public void executor(TbkCoupon tbkCoupon) {

        executor.submit(new Runnable() {

            public void run() {

                try {
                    tbkCouponService.saveTbkCoupon(tbkCoupon);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
    }
}
