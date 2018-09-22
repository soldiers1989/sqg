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
import com.soft.tbk.model.TbkCoupon;
import com.soft.tbk.service.TbkCouponService;

@Controller
@RequestMapping("/web/coupon")
public class CouponController extends BaseController{
    
    @Autowired
    private TbkCouponService tbkCouponService;

    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/h5/coupon/couponList";
    }


    @ResponseBody
    @RequestMapping("/list.json")
    public List<TbkCoupon> list(ModelMap model, HttpServletRequest request) {
        
        UserSession userSession = getUserSession(request);
        Integer userId = userSession.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        String pageNum = request.getParameter("pageNum");
        if (pageNum == null) {
            pageNum = "1";
        }
        map.put("pageNum", Integer.valueOf(pageNum));
        map.put("pageSize", 10);
        map.put("orderStr", "CREATE_TIME DESC");
        map.put("order", true);

        QueryResult<TbkCoupon> queryResult = tbkCouponService.queryTbkCoupon(map);
        
        return queryResult.getList();
    }
}
