package com.soft.wechat.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.service.TbkCouponService;
import com.soft.tbk.service.TbkRateService;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.utils.ListUtil;

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

                try {
                    tbkUserService.saveTbkUserWithOpenId(tbkUser);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        });
    }
}
