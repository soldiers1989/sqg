package com.soft.tbk.admin;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkAccount;
import com.soft.tbk.model.TbkAccountList;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkAccountListService;
import com.soft.tbk.service.TbkAccountService;
import com.soft.tbk.service.TbkUserService;

@Controller
@RequestMapping("/admin/user")
public class UserAdminController extends BaseController{
    
    @Autowired
    private TbkUserService tbkUserService;

    @Autowired
    private TbkAccountService tbkAccountService; 

    @Autowired
    private TbkAccountListService tbkAccountListService; 

    
    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/admin/user/index";
    }
    @RequestMapping("/add")
    public String add(ModelMap model, Integer id) {
        model.put("id", id);
        return "/admin/user/add";
    }
    @RequestMapping("/view")
    public String view(ModelMap model, Integer id) {
        model.put("id", id);
        return "/admin/user/view";
    }

    @ResponseBody
    @RequestMapping("/list.json")
    public QueryResult<TbkUser> list(@RequestParam Map<String, Object> params, HttpServletRequest request) {
        
        polishQueryMap(params);
        
        QueryResult<TbkUser> queryResult = tbkUserService.queryTbkUser(params);
        
        return queryResult;
    }
    
    @ResponseBody
    @RequestMapping("/get.json")
    public ResultResponse get(Integer id, HttpServletRequest request) {
        
        TbkUser tbkUser = tbkUserService.getTbkUser(id);
        TbkAccount tbkAccount = tbkAccountService.getTbkAccountByUserId(id);
        JSONObject user = (JSONObject) JSONObject.toJSON(tbkUser);
        user.put("amount", tbkAccount.getAccountAmount());
        user.put("amountA", tbkAccount.getAccountAmountA());
        user.put("amountE", tbkAccount.getAccountAmountE());
        user.put("amountF", tbkAccount.getAccountAmountF());
        return new ResultResponse(user);
    }
    @ResponseBody
    @RequestMapping("/delete.json")
    public ResultResponse delete(Integer id, HttpServletRequest request) {
        
        boolean flag = tbkUserService.deleteTbkUser(id);
        
        return new ResultResponse(flag);
    }
    
    @ResponseBody
    @RequestMapping("/add.json")
    public ResultResponse add(TbkUser tbkUser, HttpServletRequest request) {
        
        tbkUser = tbkUserService.saveTbkUser(tbkUser);
        
        return new ResultResponse(tbkUser);
    }

    @ResponseBody
    @RequestMapping("/update.json")
    public ResultResponse update(TbkUser tbkUser, HttpServletRequest request) {
        
        boolean flag = tbkUserService.updateTbkUser(tbkUser);
        
        return new ResultResponse(flag);
    }
    
    @ResponseBody
    @RequestMapping("/sendAmount.json")
    public ResultResponse sendAmount(Integer id, String amount, HttpServletRequest request) {
        
        UserSession user = getUserSession(request);
        
        if (!"admin".equals(user.getUserNickname())) {
            return new ResultResponse("error", "位置错误");
        }
        
        TbkAccount tbkAccount = tbkAccountService.getTbkAccountByUserId(id);
        tbkAccount.setAccountAmount(tbkAccount.getAccountAmount().add(new BigDecimal(amount)));
        tbkAccount.setAccountAmountA(tbkAccount.getAccountAmountA().add(new BigDecimal(amount)));
        
        boolean flag = tbkAccountService.updateTbkAccount(tbkAccount);
        
        TbkAccountList tbkAccountList = new TbkAccountList();
        tbkAccountList.setAmount(new BigDecimal(amount));
        tbkAccountList.setRemark("平台发放奖励金");
        tbkAccountList.setType("1");
        tbkAccountList.setStatus(1);
        tbkAccountList.setUserId(id);
        tbkAccountListService.saveTbkAccountList(tbkAccountList);
        
        return new ResultResponse(flag);
    }
 
    
}
