package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_account")
public class TbkAccount {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 总金额
     */
    @Column(name = "ACCOUNT_AMOUNT")
    private BigDecimal accountAmount;

    /**
     * 可用金额
     */
    @Column(name = "ACCOUNT_AMOUNT_A")
    private BigDecimal accountAmountA;

    /**
     * 冻结金额(提现中)
     */
    @Column(name = "ACCOUNT_AMOUNT_F")
    private BigDecimal accountAmountF;

    /**
     * 已赚取金额
     */
    @Column(name = "ACCOUNT_AMOUNT_E")
    private BigDecimal accountAmountE;

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
     * @return USER_ID
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取总金额
     *
     * @return ACCOUNT_AMOUNT - 总金额
     */
    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    /**
     * 设置总金额
     *
     * @param accountAmount 总金额
     */
    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    /**
     * 获取可用金额
     *
     * @return ACCOUNT_AMOUNT_A - 可用金额
     */
    public BigDecimal getAccountAmountA() {
        return accountAmountA;
    }

    /**
     * 设置可用金额
     *
     * @param accountAmountA 可用金额
     */
    public void setAccountAmountA(BigDecimal accountAmountA) {
        this.accountAmountA = accountAmountA;
    }

    /**
     * 获取冻结金额(提现中)
     *
     * @return ACCOUNT_AMOUNT_F - 冻结金额(提现中)
     */
    public BigDecimal getAccountAmountF() {
        return accountAmountF;
    }

    /**
     * 设置冻结金额(提现中)
     *
     * @param accountAmountF 冻结金额(提现中)
     */
    public void setAccountAmountF(BigDecimal accountAmountF) {
        this.accountAmountF = accountAmountF;
    }

    /**
     * 获取已赚取金额
     *
     * @return ACCOUNT_AMOUNT_E - 已赚取金额
     */
    public BigDecimal getAccountAmountE() {
        return accountAmountE;
    }

    /**
     * 设置已赚取金额
     *
     * @param accountAmountE 已赚取金额
     */
    public void setAccountAmountE(BigDecimal accountAmountE) {
        this.accountAmountE = accountAmountE;
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
     * @param updateTime 更新时间
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
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}