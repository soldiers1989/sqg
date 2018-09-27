package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.*;

@Table(name = "t_tbk_order")
public class TbkOrder {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * PID
     */
    @Column(name = "PID")
    private String pid;

    /**
     * 商品ID
     */
    @Column(name = "ITEM_ID")
    private Long itemId;

    /**
     * 商品图片
     */
    @Column(name = "ITEM_IMAGE")
    private String itemImage;

    /**
     * 商品标题
     */
    @Column(name = "ITEM_TITLE")
    private String itemTitle;

    /**
     * 商品价格
     */
    @Column(name = "ITEM_PRICE")
    private BigDecimal itemPrice;

    /**
     * 卖家名称
     */
    @Column(name = "SELLER_NAME")
    private String sellerName;

    /**
     * 淘宝订单号ID
     */
    @Column(name = "TRADE_ID")
    private Long tradeId;

    /**
     * 商品数量
     */
    @Column(name = "ITEM_NUM")
    private Integer itemNum;

    /**
     * 支付金额
     */
    @Column(name = "PAY_AMOUNT")
    private BigDecimal payAmount;

    /**
     * 预计佣金
     */
    @Column(name = "COMMISSION")
    private BigDecimal commission;

    /**
     * 预计佣金比例
     */
    @Column(name = "COMMISSION_RATE")
    private BigDecimal commissionRate;

    /**
     * 订单创建时间
     */
    @Column(name = "TRADE_TIME")
    private Date tradeTime;

    /**
     * 订单结算时间
     */
    @Column(name = "EARNING_TIME")
    private Date earningTime;

    /**
     * 订单状态
     */
    @Column(name = "TRADE_STATUS")
    private Integer tradeStatus;

    /**
     * 订单类型
     */
    @Column(name = "TRADE_TYPE")
    private String tradeType;

    /**
     * 收入比率，卖家设置佣金比率+平台补贴比率
     */
    @Column(name = "INCOME_RATE")
    private String incomeRate;

    /**
     * 补贴比率
     */
    @Column(name = "SUBSIDY_RATE")
    private String subsidyRate;

    /**
     * 成交平台，PC:1，无线:2平台
     */
    @Column(name = "RERMINAL_TYPE")
    private String rerminalType;

    /**
     * 付款金额
     */
    @Column(name = "ALIPAY_TOTAL_PRICE")
    private BigDecimal alipayTotalPrice;

    /**
     * 佣金比率
     */
    @Column(name = "TOTAL_COMMISSION_RATE")
    private String totalCommissionRate;

    /**
     * 佣金金额
     */
    @Column(name = "TOTAL_COMMISSION_FEE")
    private String totalCommissionFee;

    /**
     * 补贴金额
     */
    @Column(name = "SUBSIDY_FEE")
    private String subsidyFee;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 预计付款佣金
     */
    @Column(name = "COMMISSION_AMOUNT")
    private BigDecimal commissionAmount;

    /**
     * 预计结算佣金
     */
    @Column(name = "COMMISSION_SAMOUNT")
    private BigDecimal commissionSamount;

    public BigDecimal getCommissionAmount() {
        return commissionAmount;
    }

    public BigDecimal getCommissionSamount() {
        return commissionSamount;
    }

    public void setCommissionAmount(BigDecimal commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

    public void setCommissionSamount(BigDecimal commissionSamount) {
        this.commissionSamount = commissionSamount;
    }

    /**
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户ID
     *
     * @return USER_ID - 用户ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户ID
     *
     * @param userId
     *            用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取PID
     *
     * @return PID - PID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置PID
     *
     * @param pid
     *            PID
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取商品图片
     *
     * @return ITEM_IMAGE - 商品图片
     */
    public String getItemImage() {
        return itemImage;
    }

    /**
     * 设置商品图片
     *
     * @param itemImage
     *            商品图片
     */
    public void setItemImage(String itemImage) {
        this.itemImage = itemImage == null ? null : itemImage.trim();
    }

    /**
     * 获取商品标题
     *
     * @return ITEM_TITLE - 商品标题
     */
    public String getItemTitle() {
        return itemTitle;
    }

    /**
     * 设置商品标题
     *
     * @param itemTitle
     *            商品标题
     */
    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle == null ? null : itemTitle.trim();
    }

    /**
     * 获取商品价格
     *
     * @return ITEM_PRICE - 商品价格
     */
    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    /**
     * 设置商品价格
     *
     * @param itemPrice
     *            商品价格
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    /**
     * 获取卖家名称
     *
     * @return SELLER_NAME - 卖家名称
     */
    public String getSellerName() {
        return sellerName;
    }

    /**
     * 设置卖家名称
     *
     * @param sellerName
     *            卖家名称
     */
    public void setSellerName(String sellerName) {
        this.sellerName = sellerName == null ? null : sellerName.trim();
    }

    /**
     * 获取淘宝订单号ID
     *
     * @return TRADE_ID - 淘宝订单号ID
     */
    public Long getTradeId() {
        return tradeId;
    }

    /**
     * 设置淘宝订单号ID
     *
     * @param tradeId
     *            淘宝订单号ID
     */
    public void setTradeId(Long tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * 获取商品数量
     *
     * @return ITEM_NUM - 商品数量
     */
    public Integer getItemNum() {
        return itemNum;
    }

    /**
     * 设置商品数量
     *
     * @param itemNum
     *            商品数量
     */
    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    /**
     * 获取支付金额
     *
     * @return PAY_AMOUNT - 支付金额
     */
    public BigDecimal getPayAmount() {
        return payAmount;
    }

    /**
     * 设置支付金额
     *
     * @param payAmount
     *            支付金额
     */
    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    /**
     * 获取预计佣金
     *
     * @return COMMISSION - 预计佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置预计佣金
     *
     * @param commission
     *            预计佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取预计佣金比例
     *
     * @return COMMISSION_RATE - 预计佣金比例
     */
    public BigDecimal getCommissionRate() {
        return commissionRate;
    }

    /**
     * 设置预计佣金比例
     *
     * @param commissionRate
     *            预计佣金比例
     */
    public void setCommissionRate(BigDecimal commissionRate) {
        this.commissionRate = commissionRate;
    }

    /**
     * 获取订单创建时间
     *
     * @return TRADE_TIME - 订单创建时间
     */
    public Date getTradeTime() {
        return tradeTime;
    }

    /**
     * 设置订单创建时间
     *
     * @param tradeTime
     *            订单创建时间
     */
    public void setTradeTime(Date tradeTime) {
        this.tradeTime = tradeTime;
    }

    /**
     * 获取订单结算时间
     *
     * @return EARNING_TIME - 订单结算时间
     */
    public Date getEarningTime() {
        return earningTime;
    }

    /**
     * 设置订单结算时间
     *
     * @param earningTime
     *            订单结算时间
     */
    public void setEarningTime(Date earningTime) {
        this.earningTime = earningTime;
    }

    /**
     * 获取订单状态
     *
     * @return TRADE_STATUS - 订单状态
     */
    public Integer getTradeStatus() {
        return tradeStatus;
    }

    /**
     * 设置订单状态
     *
     * @param tradeStatus
     *            订单状态
     */
    public void setTradeStatus(Integer tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    /**
     * 获取订单类型
     *
     * @return TRADE_TYPE - 订单类型
     */
    public String getTradeType() {
        return tradeType;
    }

    /**
     * 设置订单类型
     *
     * @param tradeType
     *            订单类型
     */
    public void setTradeType(String tradeType) {
        this.tradeType = tradeType == null ? null : tradeType.trim();
    }

    /**
     * 获取收入比率，卖家设置佣金比率+平台补贴比率
     *
     * @return INCOME_RATE - 收入比率，卖家设置佣金比率+平台补贴比率
     */
    public String getIncomeRate() {
        return incomeRate;
    }

    /**
     * 设置收入比率，卖家设置佣金比率+平台补贴比率
     *
     * @param incomeRate
     *            收入比率，卖家设置佣金比率+平台补贴比率
     */
    public void setIncomeRate(String incomeRate) {
        this.incomeRate = incomeRate == null ? null : incomeRate.trim();
    }

    /**
     * 获取补贴比率
     *
     * @return SUBSIDY_RATE - 补贴比率
     */
    public String getSubsidyRate() {
        return subsidyRate;
    }

    /**
     * 设置补贴比率
     *
     * @param subsidyRate
     *            补贴比率
     */
    public void setSubsidyRate(String subsidyRate) {
        this.subsidyRate = subsidyRate == null ? null : subsidyRate.trim();
    }

    /**
     * 获取成交平台，PC:1，无线:2平台
     *
     * @return RERMINAL_TYPE - 成交平台，PC:1，无线:2平台
     */
    public String getRerminalType() {
        return rerminalType;
    }

    /**
     * 设置成交平台，PC:1，无线:2平台
     *
     * @param rerminalType
     *            成交平台，PC:1，无线:2平台
     */
    public void setRerminalType(String rerminalType) {
        this.rerminalType = rerminalType == null ? null : rerminalType.trim();
    }

    /**
     * 获取付款金额
     *
     * @return ALIPAY_TOTAL_PRICE - 付款金额
     */
    public BigDecimal getAlipayTotalPrice() {
        return alipayTotalPrice;
    }

    /**
     * 设置付款金额
     *
     * @param alipayTotalPrice
     *            付款金额
     */
    public void setAlipayTotalPrice(BigDecimal alipayTotalPrice) {
        this.alipayTotalPrice = alipayTotalPrice;
    }

    /**
     * 获取佣金比率
     *
     * @return TOTAL_COMMISSION_RATE - 佣金比率
     */
    public String getTotalCommissionRate() {
        return totalCommissionRate;
    }

    /**
     * 设置佣金比率
     *
     * @param totalCommissionRate
     *            佣金比率
     */
    public void setTotalCommissionRate(String totalCommissionRate) {
        this.totalCommissionRate = totalCommissionRate == null ? null : totalCommissionRate.trim();
    }

    /**
     * 获取佣金金额
     *
     * @return TOTAL_COMMISSION_FEE - 佣金金额
     */
    public String getTotalCommissionFee() {
        return totalCommissionFee;
    }

    /**
     * 设置佣金金额
     *
     * @param totalCommissionFee
     *            佣金金额
     */
    public void setTotalCommissionFee(String totalCommissionFee) {
        this.totalCommissionFee = totalCommissionFee == null ? null : totalCommissionFee.trim();
    }

    /**
     * 获取 补贴金额
     *
     * @return SUBSIDY_FEE - 补贴金额
     */
    public String getSubsidyFee() {
        return subsidyFee;
    }

    /**
     * 设置 补贴金额
     *
     * @param subsidyFee
     *            补贴金额
     */
    public void setSubsidyFee(String subsidyFee) {
        this.subsidyFee = subsidyFee == null ? null : subsidyFee.trim();
    }

    /**
     * 获取更新时间
     *
     * @return UPDATE_TIME - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime
     *            更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取创建时间
     *
     * @return CREATE_TIME - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime
     *            创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

}