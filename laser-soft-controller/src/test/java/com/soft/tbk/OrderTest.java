package com.soft.tbk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.utils.ListUtil;
import com.soft.wechat.service.BusinessService;
import com.soft.wechat.service.TbkCoreService;
import com.soft.wechat.util.WebUtils;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    
    @Autowired
    private TbkCoreService tbkCoreService;
    
    @Autowired
    private BusinessService businessService;
    
    public static final String URL = "http://open.jxb001.cn/openApi/order/api";

    
    @Test
    public void testOrder() {
        
        
        Map<String, Object> orderMap = new HashMap<String, Object>();
        orderMap.put("appkey", "1822410736221674");
        orderMap.put("appsecret", "1c6f031ac7bcf150ea983712fc413af2");
        orderMap.put("startTime", "2018-09-30 16:35:17");
        orderMap.put("span", "600");
        orderMap.put("pageNo", "1");
        orderMap.put("pageSize", "100");
        orderMap.put("tkStatus", "1");
        orderMap.put("orderQueryType", "settle_time");
        
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
                    tbkOrder.setTradeParentId(orderJson.getLong("trade_parent_id"));
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
            
        } catch (Exception e) {
        }
    }
}
