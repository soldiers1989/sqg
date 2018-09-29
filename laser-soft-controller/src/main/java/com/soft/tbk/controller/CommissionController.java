package com.soft.tbk.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.model.TbkOrder;
import com.soft.tbk.service.TbkOrderService;
import com.soft.tbk.utils.DateUtil;
import com.soft.tbk.utils.ReadExcelTools;
import com.soft.wechat.service.BusinessService;
import com.soft.wechat.service.TbkCoreService;

@RestController
@RequestMapping("/web/commission")
public class CommissionController extends BaseController{
    
    
    @Autowired
    private TbkOrderService tbkOrderService;
    
    @Autowired
    private TbkCoreService tbkCoreService;

    @Autowired
    private BusinessService businessService;

    @ResponseBody
    @RequestMapping("/excelupload")
    public ResultResponse excelupload(HttpServletRequest request) {
        
        try {  
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;  
            List<MultipartFile> fileList = multipartRequest.getFiles("file");
            List<String[]> result = null;
            for (MultipartFile mf : fileList) {  
               
                result = ReadExcelTools.readExcel(mf);
                
            }  
            List<TbkOrder> storeOrderList = new ArrayList<TbkOrder>();

            if (result != null) {
                for (String[] order : result) {
                    TbkOrder tbkOrder = new TbkOrder();
                    tbkOrder.setTradeTime(DateUtil.getDateToString(order[0], DateUtil.DATETIMESHOWFORMAT));
                    tbkOrder.setItemTitle(order[2]);
                    tbkOrder.setItemId(new Long(order[3]));
                    tbkOrder.setSellerName(order[5]);
                    tbkOrder.setItemNum(new Integer(order[6]));
                    tbkOrder.setItemPrice(new BigDecimal(order[7]));
                    String tkStatus = order[8];
                    Integer status = 0;
                    tbkOrder.setCommission(new BigDecimal(order[13]));
                    
                    if (tkStatus.equals("订单失效")) {
                        status = TbkConstants.TRADE_STATUS_13;
                    }else if (tkStatus.equals("订单结算")) {
                        status = TbkConstants.TRADE_STATUS_3;
                        tbkOrder.setCommission(new BigDecimal(order[18]));
                    }else if (tkStatus.equals("订单付款")) {
                        status = TbkConstants.TRADE_STATUS_12;
                    }else if (tkStatus.equals("订单成功")) {
                        status = TbkConstants.TRADE_STATUS_14;
                    }
                    tbkOrder.setTradeStatus(status);
                    tbkOrder.setAlipayTotalPrice(new BigDecimal(order[12]));
                    tbkOrder.setPayAmount(new BigDecimal(order[12]));
                    
                    tbkOrder.setCommissionRate(new BigDecimal(order[17].replace("%", "").trim()).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_DOWN));
                    
                    String incomeRateStr = order[10];

                    tbkOrder.setIncomeRate(new BigDecimal(incomeRateStr.replace("%", "").trim()).divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_DOWN).toString());
                    
                    tbkOrder.setPid("mm_47328993_" + order[27] + "_" + order[29]);
                    
                    tbkOrder.setRerminalType("2"); 
                    tbkOrder.setTradeParentId(new Long(order[25])); //26
                    // 订单状态，1: 全部订单(默认)，3：订单结算，12：订单付款， 13：订单失效，14：订单成功；
                    tbkOrder.setTradeType(order[9]);
                    
                    tbkOrder.setTotalCommissionRate(new BigDecimal(order[17].replace("%", "").trim()).divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_DOWN).toString());
                    tbkOrder.setTotalCommissionFee(new BigDecimal(order[13]).toString());
                    
                    tbkOrder.setSubsidyFee(order[22]);   //22
                    tbkOrder.setSubsidyRate(order[21]); //21
                    tbkOrder.setEarningTime(DateUtil.getDateToString(order[16], DateUtil.DATETIMESHOWFORMAT));
                    
                    storeOrderList.add(tbkOrder);
                }
                businessService.batchSettleOrderList(storeOrderList);
            }

        } catch (Exception e) {  
            logger.error(e.getMessage(), e);
        }  
        
        return new ResultResponse();    
    }
}
