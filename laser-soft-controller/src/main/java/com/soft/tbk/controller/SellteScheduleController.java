package com.soft.tbk.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.service.TbkCommissionService;
import com.soft.tbk.utils.ListUtil;
import com.soft.wechat.service.TbkCoreService;

@RestController
@RequestMapping("/web/sellte")
public class SellteScheduleController extends BaseController {

    public static final String URL = "http://open.jxb001.cn/openApi/order/api";


    @Autowired
    private TbkCommissionService tbkCommissionService;

    @Autowired
    private TbkCoreService tbkCoreService;
    
    
    @RequestMapping("/confirm")
    public ResultResponse confirm(HttpServletRequest request) {

        logger.info("佣金结算开始");

        List<Map<String, Object>> sellteCommissionList = tbkCommissionService.sellteCommission(getMonthDate(-1));
        
        if (ListUtil.isNotEmpty(sellteCommissionList)) {
            for (Map<String, Object> sellteCommission : sellteCommissionList) {
                try {
                    Integer userId = Integer.valueOf(sellteCommission.get("userId").toString());
                    BigDecimal settleAmount = new BigDecimal(sellteCommission.get("sumAmount").toString());
                    if (settleAmount.compareTo(BigDecimal.ZERO) > 0) {
                        tbkCoreService.settelCommission(userId, settleAmount, getMonthDate(-1));
                    }
                } catch (Exception e) {
                    logger.error("佣金结算错误" , e);
                }
            }
        }
         
        logger.info("佣金结算结束");

        return new ResultResponse();
    }

}
