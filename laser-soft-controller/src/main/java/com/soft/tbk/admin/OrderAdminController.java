package com.soft.tbk.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.service.TbkOrderService;

@Controller
@RequestMapping("/admin/order")
public class OrderAdminController extends BaseController{
    
    @Autowired
    private TbkOrderService tbkOrderService;

    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/admin/order/index";
    }
    @RequestMapping("/add")
    public String add(ModelMap model, Integer id) {
        model.put("id", id);
        return "/admin/order/add";
    }
    @RequestMapping("/view")
    public String view(ModelMap model, Integer id) {
        model.put("id", id);
        return "/admin/order/view";
    }

    @ResponseBody
    @RequestMapping("/list.json")
    public QueryResult<TbkOrder> list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        
        polishQueryMap(params);
        
        QueryResult<TbkOrder> queryResult = tbkOrderService.queryTbkOrder(params);
        
        return queryResult;
    }
    
    @ResponseBody
    @RequestMapping("/get.json")
    public ResultResponse get(Integer id, HttpServletRequest request) {
        
        TbkOrder tbkOrder = tbkOrderService.getTbkOrder(id);
        
        return new ResultResponse(tbkOrder);
    }
    @ResponseBody
    @RequestMapping("/delete.json")
    public ResultResponse delete(Integer id, HttpServletRequest request) {
        
        boolean flag = tbkOrderService.deleteTbkOrder(id);
        
        return new ResultResponse(flag);
    }
    
    @ResponseBody
    @RequestMapping("/add.json")
    public ResultResponse add(TbkOrder tbkOrder, HttpServletRequest request) {
        
        tbkOrder = tbkOrderService.saveTbkOrder(tbkOrder);
        
        return new ResultResponse(tbkOrder);
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public ResultResponse update(TbkOrder tbkOrder, HttpServletRequest request) {
        
        boolean flag = tbkOrderService.updateTbkOrder(tbkOrder);
        
        return new ResultResponse(flag);
    }

    
}
