package com.soft.wechat.service;

import org.springframework.stereotype.Service;

import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.model.TbkUser;

@Service
public interface TbkCoreService {

    /**
     * 
     * @param openid
     * @return
     */
    public TbkUser loadTbkUserInfo(String openid, Integer parentUserId);

    public TbkCoupon createTbkCoupon(String tkl, TbkUser tbkUser);

    /**
     * 订单保存
     * @param tbkOrder
     */
    public void saveOrder(TbkOrder tbkOrder);
    
    /**
     * 订单结算
     * @param tbkOrder
     */
    public void settleOrder(TbkOrder tbkOrder);
}
