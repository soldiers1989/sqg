package com.soft.tbk.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.utils.DateUtil;
import com.soft.tbk.utils.ListUtil;
import com.soft.wechat.service.BusinessService;
import com.soft.wechat.service.TbkCoreService;
import com.soft.wechat.util.WebUtils;

@RestController
@RequestMapping("/web/order")
public class OrderController extends BaseController {

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
        orderMap.put("span", "");
        orderMap.put("pageNo", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tkStatus", "1");
        orderMap.put("orderQueryType", "create_time");

        try {

            String result = WebUtils.doGet(URL, orderMap);

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

    /**
     * 每月20号触发,当前每2分钟循环一次，查询上个月有效订单
     * 
     * @param request
     * @return
     */
    @RequestMapping("/saveCommisson")
    public ResultResponse saveCommisson(HttpServletRequest request) {

        logger.info("有效订单生成佣金开始");
        Map<String, Object> orderMap = new HashMap<String, Object>();
        Date now = new Date();
        int year = DateUtil.getYear(now);
        int month = DateUtil.getMonth(now);
        orderMap.put("pageNum", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tradeStatus", 3);
        orderMap.put("startDate", DateUtil.getFirstDayOfMonth(year, month - 1));
        orderMap.put("endDate", DateUtil.getLastDayOfMonth(year, month - 1));
        QueryResult<TbkOrder> queryResult = tbkOrderService.queryTbkOrder(orderMap);
        if (queryResult != null && queryResult.getList() != null && !queryResult.getList().isEmpty()) {
            List<TbkOrder> orderList = queryResult.getList();
            for (TbkOrder tbkOrder : orderList) {
                String totalCommissionFee = tbkOrder.getTotalCommissionFee();
                if (StringUtils.isBlank(totalCommissionFee)) {
                    continue;
                }
                try {
                    tbkCoreService.saveCommissionList(new BigDecimal(totalCommissionFee), tbkOrder.getUserId(), tbkOrder.getId());
                    // 成功后将订单状态改为已结算
                    tbkOrder.setEarningTime(now);
                    tbkOrder.setTradeStatus(10);
                    tbkOrderService.updateTbkOrder(tbkOrder);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        logger.info("有效订单生成佣金结束");
        return new ResultResponse();
    }

    public static void main(String[] args) {

        Date now = new Date();
        Date now_10 = new Date(now.getTime() - 600000);

        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("appkey", "1822410736221674");
        orderMap.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        orderMap.put("startTime", DateUtil.getDateString(now_10, DateUtil.DATETIMESHOWFORMAT));
        orderMap.put("span", "");
        orderMap.put("pageNo", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tkStatus", "1");
        orderMap.put("orderQueryType", "create_time");

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
