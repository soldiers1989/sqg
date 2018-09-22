package com.soft.tbk;

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
        tbkOrder.setTradeId(221981996150579004L);
        tbkOrder.setTotalCommissionFee("3.54");
        tbkOrder.setTradeStatus(3);
        tbkOrder.setUserId(5);
        tbkOrder.setItemId(549239069755L);
        tbkCoreService.settleOrder(tbkOrder);
    }
}
