package com.soft.wechat.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseServiceImpl;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.model.TbkCommission;
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.model.TbkPid;
import com.soft.tbk.model.TbkPidItem;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.service.TbkCouponService;
import com.soft.tbk.service.TbkOrderService;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TbkUserService tbkUserService;
    
    @Autowired
    private TbkPidItemService tbkPidItemService;

    @Autowired
    private TbkPidService tbkPidService;

    @Autowired
    private TbkOrderService tbkOrderService;

    @Autowired
    private TbkCouponService tbkCouponService;
    
    @Autowired
    private TbkRateService tbkRateService;

    @Autowired
    private TbkCommissionService tbkCommissionService;


    
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
        
        TbkPidItem tbkPidItem = getPidItemByItemId(itemId, null, userId, null);
        
        if (tbkPidItem == null) {
            tbkPidItem = getPidItemByItemId(itemId, null, null, null);
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
            userTbkPidItem = getPidItemByItemId(itemId, null, null, orderStr);
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

    private TbkPidItem getPidItemByItemId(String itemId, String pid, Integer userId, String orderStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("itemId", Long.valueOf(itemId));
        if (userId != null) {
            map.put("userId", userId);
        }
        if (pid != null) {
            map.put("pid", pid);
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

    @Override
    public void saveOrder(TbkOrder tbkOrder) {
        
        if (tbkOrder == null) 
            return;
        
        QueryResult<TbkOrder> queryReulst = tbkOrderService.queryTbkOrder(getQueryParamMap("tradeId", tbkOrder.getTradeId()));
        
        if (queryReulst != null && ListUtil.isNotEmpty(queryReulst.getList())) {
            return;
        }
        
        TbkPidItem tbkPidItem = getPidItemByItemId(tbkOrder.getItemId().toString(), tbkOrder.getPid(), null, null);
        
        if (tbkPidItem != null) {
            
            tbkOrder.setUserId(tbkPidItem.getUserId());
            
        }
        
        tbkOrderService.saveTbkOrder(tbkOrder);
        
    }
    
    
    /**
     * 封装佣金记录
     * 
     * @param amount 总佣金
     * @param userId 用户id
     */
    public void saveCommissionList(BigDecimal amount, Integer userId, Integer orderId) {

        try {
            List<TbkCommission> list = new ArrayList<>();
            list.add(makeCommission(amount, userId, TbkConstants.RATE_LEVEL_0, orderId));// 自己的佣金
            TbkUser tbkUser = tbkUserService.getTbkUser(userId);
            if (tbkUser != null && StringUtils.isNotBlank(tbkUser.getParentIdPath())) {
                String parentIdPath = tbkUser.getParentIdPath();
                String[] userIds = parentIdPath.split(",");
                if (userIds.length > 2) {
                    logger.error("分佣级别有误，请查看用户id:" + userId);
                } else {
                    if (userIds.length == 1) {
                        // 一级佣金
                        TbkCommission tbkCommission = makeCommission(amount, Integer.parseInt(userIds[0]), TbkConstants.RATE_LEVEL_1, orderId);
                        if (tbkCommission != null) {
                            tbkCommission.setRelationUserId(userId);
                            list.add(tbkCommission);
                        }
                    }
                    if (userIds.length == 2) {
                        // 二级佣金
                        TbkCommission tbkCommission2 = makeCommission(amount, Integer.parseInt(userIds[0]), TbkConstants.RATE_LEVEL_2, orderId);
                        if (tbkCommission2 != null) {
                            tbkCommission2.setRelationUserId(userId);
                            list.add(tbkCommission2);
                        }
                        TbkCommission tbkCommission1 = makeCommission(amount, Integer.parseInt(userIds[1]), TbkConstants.RATE_LEVEL_1, orderId);
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

    private TbkCommission makeCommission(BigDecimal amount, Integer userId, Integer rateLevel, Integer orderId) {

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
        tbkCommission.setOrderId(orderId);
        tbkCommission.setUserId(userId);
        tbkCommission.setCommission(amount.multiply(rate).divide(new BigDecimal(100), 2));
        tbkCommission.setCreateTime(new Date());
        tbkCommission.setUpdateTime(new Date());
        return tbkCommission;
    }

}
