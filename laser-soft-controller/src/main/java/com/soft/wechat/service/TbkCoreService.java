package com.soft.wechat.service;

import java.math.BigDecimal;

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

    public void saveOrder(TbkOrder tbkOrder);

    public void saveCommissionList(BigDecimal amount, Integer userId, Integer orderId);
}
