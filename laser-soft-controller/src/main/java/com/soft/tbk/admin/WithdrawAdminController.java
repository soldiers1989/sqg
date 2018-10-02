package com.soft.tbk.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.domain.QueryResult;
import com.soft.tbk.model.TbkAccountList;
import com.soft.tbk.model.TbkWithdraw;
import com.soft.tbk.service.TbkAccountListService;
import com.soft.tbk.service.TbkWithdrawService;

@Controller
@RequestMapping("/admin/withdraw")
public class WithdrawAdminController extends BaseController {

    @Autowired
    private TbkWithdrawService tbkWithdrawService;

    @Autowired
    private TbkAccountListService tbkAccountListService;

    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/admin/withdraw/index";
    }

    @ResponseBody
    @RequestMapping("/list")
    public QueryResult<TbkWithdraw> list(@RequestParam Map<String, Object> params, HttpServletRequest request) {

        polishQueryMap(params);
        QueryResult<TbkWithdraw> queryResult = tbkWithdrawService.queryTbkWithdraw(params);
        return queryResult;
    }

    @ResponseBody
    @RequestMapping("/review")
    public ResultResponse review(@RequestParam String ids, @RequestParam Integer status, HttpServletRequest request) {

        ResultResponse resultResponse = new ResultResponse();
        if (StringUtils.isBlank(ids)) {
            resultResponse.setCode(ResultResponse.ERROR);
            resultResponse.setMsg("参数不能为空!");
            return resultResponse;
        }

        List<TbkAccountList> accountListList = new ArrayList<>();
        for (String id : ids.split(",")) {
            TbkWithdraw tbkWithdraw = tbkWithdrawService.getTbkWithdraw(Integer.parseInt(id));
            Integer oldstatus = tbkWithdraw.getStatus();
            if (oldstatus != 0) {
                continue;
            }
            tbkWithdraw.setStatus(status);
            tbkWithdrawService.updateTbkWithdraw(tbkWithdraw);
            if (status == 1) {
                // 审核通过
                TbkAccountList tbkAccountList = new TbkAccountList();
                tbkAccountList.setAmount(tbkWithdraw.getAmount());
                tbkAccountList.setUserId(tbkWithdraw.getUserId());
                tbkAccountList.setType(TbkConstants.ACCOUNT_TYPE_0);
                tbkAccountList.setCode(tbkWithdraw.getId().toString());
                accountListList.add(tbkAccountList);
            }
        }
        tbkAccountListService.insertBatch(accountListList);
        return resultResponse;
    }
}
