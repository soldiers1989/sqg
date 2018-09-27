package com.soft.tbk.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.utils.DateUtil;
import com.soft.tbk.utils.ListUtil;
import com.soft.wechat.service.BusinessService;
import com.soft.wechat.service.TbkCoreService;
import com.soft.wechat.util.WebUtils;

@RestController
@RequestMapping("/web/order")
public class OrderScheduleController extends BaseController {

    public static final String URL = "http://open.jxb001.cn/openApi/order/api";

    @Autowired
    private TbkCoreService tbkCoreService;

    @Autowired
    private TbkOrderService tbkOrderService;

    @Autowired
    private BusinessService businessService;

    @RequestMapping("/sync")
    public ResultResponse sync(HttpServletRequest request) {

        logger.info("订单同步开始");

        Map<String, Object> orderMap = new HashMap<String, Object>();
        Date now = new Date();
        Date now_10 = new Date(now.getTime() - 600000);

        orderMap.put("appkey", "1822410736221674");
        orderMap.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        orderMap.put("startTime", DateUtil.getDateString(now_10, DateUtil.DATETIMESHOWFORMAT));
        orderMap.put("span", "600");
        orderMap.put("pageNo", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tkStatus", "1");
        orderMap.put("orderQueryType", "create_time");

        try {

            String result = WebUtils.doGet(URL, orderMap);

            logger.info(result);
            
            JSONObject orderResult = JSONObject.parseObject(result);

            JSONArray orderList = orderResult.getJSONArray("data");

            List<TbkOrder> storeOrderList = new ArrayList<TbkOrder>();

            if (ListUtil.isNotEmpty(orderList)) {
                for (Object order : orderList) {
                    JSONObject orderJson = (JSONObject) order;
                    TbkOrder tbkOrder = new TbkOrder();
                    tbkOrder.setAlipayTotalPrice(orderJson.getBigDecimal("alipay_total_price"));
                    tbkOrder.setItemId(orderJson.getLong("num_iid"));
                    tbkOrder.setCommission(orderJson.getBigDecimal("pub_share_pre_fee"));
                    tbkOrder.setCommissionRate(orderJson.getBigDecimal("total_commission_rate"));
                    tbkOrder.setIncomeRate(orderJson.getString("income_rate"));
                    tbkOrder.setItemNum(orderJson.getInteger("item_num"));
                    tbkOrder.setItemPrice(orderJson.getBigDecimal("price"));
                    tbkOrder.setItemTitle(orderJson.getString("item_title"));
                    tbkOrder.setPayAmount(orderJson.getBigDecimal("pay_price"));
                    tbkOrder.setPid("mm_47328993_" + orderJson.getString("site_id") + "_" + orderJson.getString("adzone_id"));
                    tbkOrder.setRerminalType(orderJson.getString("terminal_type"));
                    tbkOrder.setSellerName(orderJson.getString("seller_shop_title"));
                    tbkOrder.setTradeId(orderJson.getLong("trade_id"));
                    // 订单状态，1: 全部订单(默认)，3：订单结算，12：订单付款， 13：订单失效，14：订单成功；
                    tbkOrder.setTradeStatus(orderJson.getInteger("tk_status"));
                    tbkOrder.setTradeTime(orderJson.getDate("create_time"));
                    tbkOrder.setTradeType(orderJson.getString("order_type"));
                    tbkOrder.setTotalCommissionRate(orderJson.getString("total_commission_rate"));
                    tbkOrder.setTotalCommissionFee(orderJson.getString("total_commission_fee"));
                    tbkOrder.setSubsidyFee(orderJson.getString("subsidy_fee"));
                    tbkOrder.setSubsidyRate(orderJson.getString("subsidy_rate"));
                    storeOrderList.add(tbkOrder);
                }
            }
            businessService.batchOrderList(storeOrderList);

            logger.info("订单同步结束");

        } catch (Exception e) {
            logger.error("OrderController.sync", e);
        }

        return new ResultResponse();
    }

    @RequestMapping("/settle")
    public ResultResponse settle(HttpServletRequest request) {

        logger.info("订单结算同步开始");
        
        Map<String, Object> orderMap = new HashMap<String, Object>();
        Date now = new Date();
        Date now_10 = new Date(now.getTime() - 600000);

        orderMap.put("appkey", "1822410736221674");
        orderMap.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        orderMap.put("startTime", DateUtil.getDateString(now_10, DateUtil.DATETIMESHOWFORMAT));
        orderMap.put("span", "600");
        orderMap.put("pageNo", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tkStatus", "1");
        orderMap.put("orderQueryType", "settle_time");
        
        try {
            
            String result = WebUtils.doGet(URL, orderMap);
            
            logger.info(result);
            
            JSONObject orderResult = JSONObject.parseObject(result);
            
            JSONArray orderList = orderResult.getJSONArray("data");

            List<TbkOrder> storeOrderList = new ArrayList<TbkOrder>();
            
            if (ListUtil.isNotEmpty(orderList)) {
                for (Object order : orderList) {
                    JSONObject orderJson = (JSONObject) order;
                    TbkOrder tbkOrder = new TbkOrder();
                    tbkOrder.setAlipayTotalPrice(orderJson.getBigDecimal("alipay_total_price"));
                    tbkOrder.setItemId(orderJson.getLong("num_iid"));
                    tbkOrder.setCommission(orderJson.getBigDecimal("commission"));
                    tbkOrder.setCommissionRate(orderJson.getBigDecimal("commission_rate"));
                    tbkOrder.setIncomeRate(orderJson.getString("income_rate"));
                    tbkOrder.setItemNum(orderJson.getInteger("item_num"));
                    tbkOrder.setItemPrice(orderJson.getBigDecimal("price"));
                    tbkOrder.setItemTitle(orderJson.getString("item_title"));
                    tbkOrder.setPayAmount(orderJson.getBigDecimal("pay_price"));
                    tbkOrder.setPid("mm_47328993_"+orderJson.getString("site_id")+"_"+orderJson.getString("adzone_id"));
                    tbkOrder.setRerminalType(orderJson.getString("terminal_type"));
                    tbkOrder.setSellerName(orderJson.getString("seller_shop_title"));
                    tbkOrder.setTradeId(orderJson.getLong("trade_id"));
                    tbkOrder.setTradeStatus(orderJson.getInteger("tk_status"));
                    tbkOrder.setTradeTime(orderJson.getDate("create_time"));
                    tbkOrder.setTradeType(orderJson.getString("order_type"));
                    tbkOrder.setTotalCommissionRate(orderJson.getString("total_commission_rate"));
                    tbkOrder.setTotalCommissionFee(orderJson.getString("total_commission_fee"));
                    tbkOrder.setSubsidyFee(orderJson.getString("subsidy_fee"));
                    tbkOrder.setSubsidyRate(orderJson.getString("subsidy_rate"));
                    tbkOrder.setEarningTime(orderJson.getDate("earning_time"));
                    storeOrderList.add(tbkOrder);
                }
            }
            businessService.batchSettleOrderList(storeOrderList);
            
            logger.info("订单结算同步结束");

        } catch (Exception e) {
            logger.error("OrderController.sync", e);
        }
        
        return new ResultResponse();
    }

    
    public static void main(String[] args) {

        Date now = new Date();
        Date now_10 = new Date(now.getTime() - 600000);

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("appkey", "1822410736221674");
        orderMap.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        orderMap.put("startTime", "2018-09-25 11:00:00");
        orderMap.put("span", "600");
        orderMap.put("pageNo", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tkStatus", "1");
        orderMap.put("orderQueryType", "settle_time");

        try {

            String result = WebUtils.doGet(URL, orderMap);

            JSONObject order = JSONObject.parseObject(result);

            JSONArray orderList = order.getJSONArray("data");

            if (ListUtil.isNotEmpty(orderList)) {

            }

        } catch (IOException e) {

        }
    }
}
