package com.soft.tbk.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.core.cache.IRedisClientKValue;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkAccount;
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.model.TbkWithdraw;
import com.soft.tbk.service.TbkAccountService;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.service.TbkWithdrawService;

@Controller
@RequestMapping("/web/wallet")
public class WalletController extends BaseController {

    private static final BigDecimal minWithdraw = new BigDecimal(5);

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

    @Autowired
    private IRedisClientKValue<String> redisClientKValue;

    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        user.setUserLevel("1");
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
        Map<String, Object> beforeMap = tbkOrderService.sumCommsionAndCount(user.getId(),
                        new Date(System.currentTimeMillis() - (24 * 60 * 60 * 1000)));
        if (!beforeMap.containsKey("sumAmount")) {
            beforeMap.put("sumAmount", 0);
        }
        model.put("now", nowMap);
        model.put("now_1", beforeMap);

        Map<String, Object> nowSumMap = makeSumMap();
        Map<String, Object> nowTeamMap = makeSumMap();
        Map<String, Object> beforeSumMap = makeSumMap();
        Map<String, Object> beforeTeamMap = makeSumMap();
        
        List<Map<String, Object>> nowSumList = tbkCommissionService.sumCommission(user.getId(), new Date());
        for (Map<String, Object> smap : nowSumList) {
            if (TbkConstants.RATE_LEVEL_0.toString().equals(smap.get("commissionType").toString())) {
                nowSumMap.putAll(smap); 
            }
            if (TbkConstants.RATE_LEVEL_1.toString().equals(smap.get("commissionType").toString()) || TbkConstants.RATE_LEVEL_2.toString().equals(smap.get("commissionType").toString())) {
                nowTeamMap.put("count", Integer.valueOf(nowTeamMap.get("count").toString())  + Integer.valueOf(smap.get("count").toString()));
                nowTeamMap.put("sumAmount", new BigDecimal(nowTeamMap.get("sumAmount").toString()).add(new BigDecimal(smap.get("sumAmount").toString())).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            }
        }
        
        List<Map<String, Object>> nowSumList_1 = tbkCommissionService.sumCommission(user.getId(), getMonthDate(-1));
        for (Map<String, Object> smap : nowSumList_1) {
            if (TbkConstants.RATE_LEVEL_0.toString().equals(smap.get("commissionType").toString())) {
                beforeSumMap.putAll(smap); 
            }
            if (TbkConstants.RATE_LEVEL_1.toString().equals(smap.get("commissionType").toString()) || TbkConstants.RATE_LEVEL_2.toString().equals(smap.get("commissionType").toString())) {
                beforeTeamMap.put("count", Integer.valueOf(beforeTeamMap.get("count").toString())  + Integer.valueOf(smap.get("count").toString()));
                beforeTeamMap.put("sumAmount", new BigDecimal(beforeTeamMap.get("sumAmount").toString()).add(new BigDecimal(smap.get("sumAmount").toString())).setScale(2, BigDecimal.ROUND_HALF_DOWN));
            }
        }
        model.put("nowSum", nowSumMap);
        model.put("nowTeam", nowTeamMap);
        model.put("nowSum_1", beforeSumMap);
        model.put("nowTeam_1", beforeTeamMap);
        return "/h5/wallet/index";
    }

    private Map<String, Object> makeSumMap() {
        Map<String, Object> sumMap = new HashMap<String, Object>();
        sumMap.put("count", 0);
        sumMap.put("sumAmount", 0);
        return sumMap;
    }
    
    @RequestMapping("/cash")
    public String cash(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        if (StringUtils.isBlank(user.getUserPhone())) {
            return "/h5/wallet/phone";
        }
        Integer id = user.getId();
        TbkUser tbkUser = tbkUserService.getTbkUser(id);
        model.put("user", tbkUser);
        model.put("minWithdraw", minWithdraw);
        model.put("phoneNo", tbkUser.getUserPhone());
        TbkAccount account = tbkAccountService.getTbkAccountByUserId(id);
        BigDecimal abAmount = BigDecimal.ZERO;
        if (account != null) {
            abAmount = account.getAccountAmountA();
        }
        model.put("amount", abAmount);
        return "/h5/wallet/cash";
    }

    @ResponseBody
    @RequestMapping("/list.json")
    public List<TbkWithdraw> list(HttpServletRequest request) {

        QueryResult<TbkWithdraw> queryResult = query(request);
        return queryResult.getList();
    }

    private QueryResult<TbkWithdraw> query(HttpServletRequest request) {

        UserSession userSession = getUserSession(request);
        Integer userId = userSession.getId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userId", userId);
        String pageNum = request.getParameter("pageNum");
        if (StringUtils.isBlank(pageNum)) {
            pageNum = "1";
        }
        String rows = request.getParameter("rows");
        if (StringUtils.isBlank(rows)) {
            rows = "8";
        }
        map.put("pageNum", Integer.valueOf(pageNum));
        map.put("pageSize", Integer.valueOf(rows));
        map.put("orderStr", "ID DESC");
        map.put("order", true);
        return tbkWithdrawService.queryTbkWithdraw(map);
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
        setUserSession(request, user);
        return new ResultResponse();
    }

    /**
     * 绑定手机号码
     * 
     * @param request
     * @param phoneNo
     * @param code
     * @return
     */
    @RequestMapping("/updatePhone")
    @ResponseBody
    public ResultResponse savePhone(HttpServletRequest request, String phoneNo, String code) {

        ResultResponse resultResponse = new ResultResponse();
        if (!checkCode(phoneNo, code)) {
            resultResponse.setCode(ResultResponse.ERROR);
            resultResponse.setMsg("验证码错误或已失效!");
            return resultResponse;
        }
        UserSession user = getUserSession(request);
        user.setUserPhone(phoneNo);
        tbkUserService.updateTbkUser(user);
        setUserSession(request, user);
        return new ResultResponse();
    }

    /**
     * 验证码校验
     * 
     * @param phoneNo
     * @param code
     * @return
     */
    private boolean checkCode(String phoneNo, String code) {

        String key = "code-" + phoneNo;
        String cacheCode = redisClientKValue.get(appkey, key, String.class);
        if (StringUtils.isBlank(code) || !code.equals(cacheCode)) {
            return false;
        } else {
            redisClientKValue.delete(appkey, key);
            return true;
        }
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
    public ResultResponse withdraw(HttpServletRequest request, String userAlipayAccount, BigDecimal amount, String code) {

        ResultResponse resultResponse = new ResultResponse();
        UserSession user = getUserSession(request);
        TbkAccount tbkAccount = tbkAccountService.getTbkAccountByUserId(user.getId());
        if (amount.compareTo(minWithdraw) == -1) {
            resultResponse.setCode(ResultResponse.ERROR);
            resultResponse.setMsg("提现金额小于最小提现额");
            return resultResponse;
        }
        if (amount.compareTo(tbkAccount.getAccountAmountA()) == 1) {
            resultResponse.setCode(ResultResponse.ERROR);
            resultResponse.setMsg("提现金额大于可用余额");
            return resultResponse;
        }
        if (!checkCode(user.getUserPhone(), code)) {
            resultResponse.setCode(ResultResponse.ERROR);
            resultResponse.setMsg("验证码错误或已失效!");
            return resultResponse;
        }
        //  生成提现记录
        TbkWithdraw tbkWithdraw = new TbkWithdraw();
        tbkWithdraw.setAmount(amount);
        tbkWithdraw.setUserId(user.getId());
        tbkWithdraw.setAccount(userAlipayAccount);
        tbkWithdrawService.saveTbkWithdraw(tbkWithdraw);

        // 修改账户资金
        tbkAccount.setAccountAmountA(tbkAccount.getAccountAmountA().subtract(amount));
        tbkAccount.setAccountAmountF(tbkAccount.getAccountAmountF().add(amount));
        tbkAccountService.updateTbkAccount(tbkAccount);
        return new ResultResponse();
    }

}
