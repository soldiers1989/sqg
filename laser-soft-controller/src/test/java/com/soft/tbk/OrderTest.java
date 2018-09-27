package com.soft.tbk;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.soft.tbk.model.TbkOrder;
import com.soft.wechat.service.TbkCoreService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderTest {
    
    @Autowired
    private TbkCoreService tbkCoreService;

    
    @Test
    public void testOrder() {
        
        
        TbkOrder tbkOrder = new TbkOrder();
        tbkOrder.setTradeId(2221981996150579005L);
        tbkOrder.setTotalCommissionFee("3.54");
        tbkOrder.setCommission(new BigDecimal("3.54"));
        tbkOrder.setTradeStatus(3);
        tbkOrder.setUserId(5);
        tbkOrder.setItemId(565532955150L);
        tbkOrder.setTradeTime(new Date());
        tbkOrder.setEarningTime(new Date());
        tbkCoreService.saveOrder(tbkOrder);

        tbkOrder = new TbkOrder();
        tbkOrder.setTradeId(2221981996150579005L);
        tbkOrder.setTotalCommissionFee("3.54");
        tbkOrder.setCommission(new BigDecimal("3.54"));
        tbkOrder.setTradeStatus(3);
        tbkOrder.setUserId(5);
        tbkOrder.setItemId(565532955150L);
        tbkOrder.setTradeTime(new Date());
        tbkOrder.setEarningTime(new Date());
        tbkCoreService.settleOrder(tbkOrder);

    }
}
