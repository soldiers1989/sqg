package com.soft.tbk.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkAccount;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.model.TbkWithdraw;
import com.soft.tbk.service.TbkAccountService;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.service.TbkWithdrawService;

@Controller
@RequestMapping("/web/wallet")
public class WalletController extends BaseController {

    @Autowired
    private TbkAccountService tbkAccountService;

    @Autowired
    private TbkUserService tbkUserService;

    @Autowired
    private TbkCommissionService tbkCommissionService;

    @Autowired
    private TbkWithdrawService tbkWithdrawService;

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

    @RequestMapping("/cash")
    public String cash(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        Integer id = user.getId();
        TbkUser tbkUser = tbkUserService.getTbkUser(id);
        model.put("user", tbkUser);
        TbkAccount account = tbkAccountService.getTbkAccountByUserId(id);
        BigDecimal abAmount = BigDecimal.ZERO;
        if (account != null) {
            abAmount = account.getAccountAmountA();
        }
        model.put("amount", abAmount);

        return "/h5/wallet/cash";
    }

    @RequestMapping("/addAlipay")
    public String addAlipay(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        model.put("user", user);
        return "/h5/wallet/addAlipay";
    }

    @RequestMapping("/editAlipay")
    public String editAlipay(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        Integer id = user.getId();
        TbkUser tbkUser = tbkUserService.getTbkUser(id);
        model.put("user", tbkUser);
        return "/h5/wallet/editAlipay";
    }

    /**
     * 保存支付宝账号
     * 
     * @param request
     * @param userAlipayAccount
     * @return
     */
    @RequestMapping("/updateAlipay")
    @ResponseBody
    public ResultResponse saveAlipayAccount(HttpServletRequest request, String userAlipayAccount) {

        UserSession user = getUserSession(request);
        user.setUserAlipayAccount(userAlipayAccount);
        tbkUserService.updateTbkUser(user);
        return new ResultResponse();
    }

    /**
     * 提现
     * 
     * @param request
     * @param userAlipayAccount
     * @return
     */
    @RequestMapping("/withdraw")
    @ResponseBody
    public ResultResponse withdraw(HttpServletRequest request, String userAlipayAccount, BigDecimal amount) {

        UserSession user = getUserSession(request);
        //  生成提现记录
        TbkWithdraw tbkWithdraw = new TbkWithdraw();
        tbkWithdraw.setAmount(amount);
        tbkWithdraw.setUserId(user.getId());
        tbkWithdraw.setAccount(userAlipayAccount);
        tbkWithdrawService.saveTbkWithdraw(tbkWithdraw);

        // 修改账户资金
        TbkAccount tbkAccount = tbkAccountService.getTbkAccountByUserId(user.getId());
        tbkAccount.setAccountAmountA(tbkAccount.getAccountAmountA().subtract(amount));
        tbkAccount.setAccountAmountF(tbkAccount.getAccountAmountF().add(amount));
        tbkAccountService.updateTbkAccount(tbkAccount);

        // 插入资金流水  TODO

        return new ResultResponse();
    }

}
