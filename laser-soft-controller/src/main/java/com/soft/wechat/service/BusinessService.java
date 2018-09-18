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

import com.alibaba.fastjson.JSONArray;
import com.soft.tbk.constants.TbkConstants;
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

    @Autowired
    TbkCoreService tbkCoreService;

    public String returnTKL(String tkl, String openId) {

        TbkUser tbkUser = tbkCoreService.loadTbkUserInfo(openId, null);

        if (tbkUser == null) {
            logger.error(openId + "，获取用户信息失败");
        }

        TbkCoupon tbkCoupon = tbkCoreService.createTbkCoupon(tkl, tbkUser);

        return tbkCoupon.getTkl();
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
                        if (tbkCommission != null) {
                            tbkCommission.setRelationUserId(userId);
                            list.add(tbkCommission);
                        }
                    }
                    if (userIds.length == 2) {
                        // 二级佣金
                        TbkCommission tbkCommission2 = makeCommission(amount, Integer.parseInt(userIds[0]), TbkConstants.RATE_LEVEL_2);
                        if (tbkCommission2 != null) {
                            tbkCommission2.setRelationUserId(userId);
                            list.add(tbkCommission2);
                        }
                        TbkCommission tbkCommission1 = makeCommission(amount, Integer.parseInt(userIds[1]), TbkConstants.RATE_LEVEL_1);
                        if (tbkCommission1 != null) {
                            tbkCommission1.setRelationUserId(userId);
                            list.add(tbkCommission1);
                        }
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
            userLevel = TbkConstants.USER_LEVEL_1;// 默认
        }

        if (TbkConstants.RATE_LEVEL_0.equals(rateLevel)) {
            userLevel = TbkConstants.USER_LEVEL_1;
        }

        BigDecimal rate = tbkRateService.getRateByLevel(userLevel, rateLevel);
        TbkCommission tbkCommission = new TbkCommission();
        tbkCommission.setCommissionType(rateLevel.toString());
        tbkCommission.setUserId(userId);
        tbkCommission.setCommission(amount.multiply(rate).divide(new BigDecimal(100), 2));
        return tbkCommission;
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
}
