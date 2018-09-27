package com.soft.tbk.controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

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
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.service.TbkWithdrawService;
import com.soft.tbk.utils.DateUtil;

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
    private TbkOrderService tbkOrderService;

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
        
        Map<String, Object> nowMap = tbkOrderService.sumCommsionAndCount(user.getId(), new Date());
        if (!nowMap.containsKey("sumAmount")) {
            nowMap.put("sumAmount", 0);
        }
        Map<String, Object> beforeMap = tbkOrderService.sumCommsionAndCount(user.getId(), new Date(System.currentTimeMillis()-(24 * 60 * 60 * 1000)));
        if (!beforeMap.containsKey("sumAmount")) {
            beforeMap.put("sumAmount", 0);
        }
        model.put("now", nowMap);
        model.put("now_1", beforeMap);
        
        Map<String, Object> nowSumMap = tbkCommissionService.sumCommission(user.getId(), new Date());
        if (!nowSumMap.containsKey("sumAmount")) {
            nowSumMap.put("sumAmount", 0);
        }

        Map<String, Object> beforeSumMap = tbkCommissionService.sumCommission(user.getId(), getMonthDate(-1));
        if (!beforeSumMap.containsKey("sumAmount")) {
            beforeSumMap.put("sumAmount", 0);
        }

        model.put("nowSum", nowSumMap);
        model.put("nowSum_1", beforeSumMap);

        return "/h5/wallet/index";
    }

    private Date getMonthDate(int month) {
        Date date = new Date();//获取当前时间    
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, month);//当前时间前去一个月，即一个月前的时间    
        //获取一年前的时间，或者一个月前的时间   
        return calendar.getTime();
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
