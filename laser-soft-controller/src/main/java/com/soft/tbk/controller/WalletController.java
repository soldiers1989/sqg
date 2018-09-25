package com.soft.tbk.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkAccount;
import com.soft.tbk.service.TbkAccountService;
import com.soft.tbk.service.TbkCommissionService;


@Controller
@RequestMapping("/web/wallet")
public class WalletController extends BaseController{

    
    @Autowired
    private TbkAccountService tbkAccountService;
   
    @Autowired
    private TbkCommissionService tbkCommissionService;

    
    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        model.put("user", user);

        TbkAccount account = tbkAccountService.getTbkAccountByUserId(user.getId());

        BigDecimal abAmount = BigDecimal.ZERO;
        if (account != null) {
            abAmount = account.getAccountAmountA();
        }
        model.put("amount", abAmount);

        return "/h5/wallet/index";
    }

}
