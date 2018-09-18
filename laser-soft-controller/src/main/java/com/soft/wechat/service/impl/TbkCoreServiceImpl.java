package com.soft.wechat.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseServiceImpl;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkPid;
import com.soft.tbk.model.TbkPidItem;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkCouponService;
import com.soft.tbk.service.TbkPidItemService;
import com.soft.tbk.service.TbkPidService;
import com.soft.tbk.service.TbkRateService;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.utils.ListUtil;
import com.soft.wechat.domain.CouponContext;
import com.soft.wechat.service.TbkCoreService;
import com.soft.wechat.util.Coupon;
import com.taobao.api.response.TbkItemInfoGetResponse;

@Service
public class TbkCoreServiceImpl extends BaseServiceImpl implements TbkCoreService{

    @Autowired
    private TbkUserService tbkUserService;
    
    @Autowired
    private TbkPidItemService tbkPidItemService;

    @Autowired
    private TbkPidService tbkPidService;

    @Autowired
    private TbkCouponService tbkCouponService;
    
    @Autowired
    private TbkRateService tbkRateService;

    
    @Override
    public TbkUser loadTbkUserInfo(String openid, Integer parentUserId) {

        Map<String, Object> map = new HashMap<String, Object>();
        
        map.put("userOpenid", openid);
        
        QueryResult<TbkUser> queryResult = tbkUserService.queryTbkUser(map);
        
        if (queryResult != null && queryResult.getList() != null && !queryResult.getList().isEmpty()) {
            return queryResult.getList().get(0);
        }
        
        TbkUser tbkUser = new TbkUser();
        tbkUser.setParentId(parentUserId);
        tbkUser.setUserOpenid(openid);
        
        tbkUser = tbkUserService.saveTbkUser(tbkUser);
        
        return tbkUser;
    }

    @Override
    public TbkCoupon createTbkCoupon(String tkl, TbkUser tbkUser) {

        JSONObject object = Coupon.getTBKItemByToken(tkl);
        
        String itemId = Coupon.parseItemId(object.getString("url"), tkl);
        
        TbkItemInfoGetResponse itemObject = Coupon.getItemDetail(itemId);
        
        TbkCoupon tbkCoupon = new TbkCoupon();

        if (itemObject == null || !itemObject.isSuccess()) {
            //未设置优惠券
            tbkCoupon.setTkl("该商品暂不参与优惠活动◕╭╮◕试试其它商品，也许会有意外的惊喜哦！！~~");
            return tbkCoupon;
        }
        
        Integer userId = tbkUser.getId();
        
        String pid = loadAvailablePid(userId, itemId);
        
        CouponContext context = new CouponContext();
        context.setItemId(itemId);
        context.setPid(pid);
        context.setObject(object);
        context.setTkl(tkl);
        context.setItemObject(itemObject);
        context.setTbkCoupon(tbkCoupon);
        
        BigDecimal userRate = tbkRateService.getRateByLevel(TbkConstants.USER_LEVEL_1, TbkConstants.RATE_LEVEL_0);
        
        context.setUserRate(userRate);
        
        Coupon.getHaveCouponItem(context);
        
        tbkCoupon.setUserId(userId);
        tbkCouponService.saveTbkCoupon(tbkCoupon);
        
        return tbkCoupon;
    }

    private String loadAvailablePid(Integer userId, String itemId) {
        
        TbkPidItem tbkPidItem = getPidItemByItemId(itemId, userId, null);
        
        if (tbkPidItem == null) {
            tbkPidItem = getPidItemByItemId(itemId, null, null);
        }else {
            return tbkPidItem.getPid();
        }
        
        String pid = "";
        String nextPid = "";
        TbkPid tbkPid = null;
        if (tbkPidItem != null) {
            pid = tbkPidItem.getNextPid();
            tbkPid = getTbkPidByPid(pid);
        }else {
            tbkPid  = tbkPidService.getTbkPidByIndex(0);
        }
        TbkPid nextTbkPid  = tbkPidService.getTbkPidByIndex(tbkPid.getOrderIndex());
        
        TbkPidItem userTbkPidItem = null;
        //无可用推广位
        if (nextTbkPid == null) {
            String orderStr = "UPDATE_TIME ASC";
            userTbkPidItem = getPidItemByItemId(itemId, null, orderStr);
        }
        
        try {
            if (userTbkPidItem != null) {
                userTbkPidItem.setUserId(userId);
                tbkPidItemService.updateTbkPidItem(userTbkPidItem);
            }else {
                userTbkPidItem = new TbkPidItem();
                pid = tbkPid.getPid();
                nextPid = nextTbkPid.getPid();
                userTbkPidItem.setItemId(Long.valueOf(itemId));
                userTbkPidItem.setPid(pid);
                userTbkPidItem.setUserId(userId);
                userTbkPidItem.setNextPid(nextPid);
                tbkPidItemService.saveTbkPidItem(userTbkPidItem);
            }
        } catch (Exception e) {
            if (e instanceof DuplicateKeyException) {
                return loadAvailablePid(userId, itemId);
            }
        }
        return pid;
    }

    private TbkPidItem getPidItemByItemId(String itemId, Integer userId, String orderStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemId", Long.valueOf(itemId));
        if (userId != null) {
            map.put("userId", userId);
        }
        if (orderStr != null) {
            map.put("orderStr", orderStr);
        }
        QueryResult<TbkPidItem> queryReuslt = tbkPidItemService.queryTbkPidItem(map);
        TbkPidItem tbkPidItem = null;
        if (queryReuslt != null && ListUtil.isNotEmpty(queryReuslt.getList())) {
            tbkPidItem = queryReuslt.getList().get(0);
            tbkPidItemService.updateTbkPidItem(tbkPidItem); //刷新时间
        }
        return tbkPidItem;
    }
    
    private TbkPid getTbkPidByPid(String pid) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pid", pid);
        QueryResult<TbkPid> queryReuslt = tbkPidService.queryTbkPid(map);
        TbkPid tbkPid = null;
        if (queryReuslt != null && ListUtil.isNotEmpty(queryReuslt.getList())) {
            tbkPid = queryReuslt.getList().get(0);
        }
        return tbkPid;
    }

}
