package com.soft.wechat.service;

import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.model.TbkUser;


public interface TbkCoreService {
    
    /**
     * 
     * @param openid
     * @return
     */
    public TbkUser loadTbkUserInfo(String openid, Integer parentUserId);
    
    
    public TbkCoupon createTbkCoupon(String tkl, TbkUser tbkUser);

    
    public void saveOrder(TbkOrder tbkOrder);
}
