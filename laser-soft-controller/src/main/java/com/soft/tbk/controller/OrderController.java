package com.soft.tbk.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.utils.StringUtils;

@Controller
@RequestMapping("/web/order")
public class OrderController extends BaseController{
    
    @Autowired
    private TbkOrderService tbkOrderService;

    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/h5/order/orderList";
    }


    @ResponseBody
    @RequestMapping("/list.json")
    public List<TbkOrder> list(ModelMap model, HttpServletRequest request) {
        UserSession userSession = getUserSession(request);
        Integer userId = userSession.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        String pageNum = request.getParameter("pageNum");
        String tradeStatus = request.getParameter("tradeStatus");
        if (pageNum == null) {
            pageNum = "1";
        }
        if (StringUtils.isNotBlank(tradeStatus)) {
            map.put("tradeStatus", tradeStatus);
        }
        map.put("pageNum", Integer.valueOf(pageNum));
        map.put("pageSize", 10);
        map.put("orderStr", "TRADE_TIME DESC");
        map.put("order", true);
        QueryResult<TbkOrder> queryResult = tbkOrderService.queryTbkOrder(map);
        
        return queryResult.getList();
    }
}
