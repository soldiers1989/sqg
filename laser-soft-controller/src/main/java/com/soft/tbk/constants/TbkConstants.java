package com.soft.tbk.constants;

import java.math.BigDecimal;

public class TbkConstants {

    /**
     * 佣金级别
     */
    public static final Integer RATE_LEVEL_0 = 0;// 自身佣金

    public static final Integer RATE_LEVEL_1 = 1;// 1级分佣

    public static final Integer RATE_LEVEL_2 = 2;// 2级分佣

    public static final BigDecimal DEFAULT_RATE = new BigDecimal(40);

    public static final String USER_LEVEL_0 = "0"; //用户等级

    public static final String USER_LEVEL_1 = "1"; //用户等级

    public static final String USER_LEVEL_2 = "2"; //用户等级

    public static final String USER_LEVEL_3 = "3"; //用户等级

    public static final String USER_LEVEL_4 = "4"; //用户等级

    public static final String USER_LEVEL_5 = "5"; //用户等级

    public static final String USER_LEVEL_6 = "6"; //用户等级

    public static final String USER_LEVEL_7 = "7"; //用户等级

    public static final String USER_LEVEL_8 = "8"; //用户等级

    public static final String USER_LEVEL_9 = "9"; //用户等级

    public static final Integer COMMSION_STATUS_0 = 0;//付款预估

    public static final Integer COMMSION_STATUS_1 = 1;//结算预估

    public static final Integer COMMSION_STATUS_2 = 2;//失效

    public static final Integer COMMSION_STATUS_3 = 3;//已发放
    
    public static final Integer TRADE_STATUS_13 = 13;//失效

    public static final Integer TRADE_STATUS_12 = 12;//付款

    public static final Integer TRADE_STATUS_3 = 3;//结算

    public static final Integer TRADE_STATUS_14 = 14;//订单成功

    public static final String ACCOUNT_TYPE_0 = "0";//提现流水

    public static final String ACCOUNT_TYPE_1 = "1";//佣金流水

    public static final String QR_TYPE_0 = "0";//臨時二維碼

    public static final String QR_TYPE_1 = "1";//永久二維碼
}
