package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "t_tbk_withdraw")
public class TbkWithdraw {

    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 提现流水号
     */
    @Column(name = "CODE")
    private String code;

    /**
     * 提现金额
     */
    @Column(name = "AMOUNT")
    private BigDecimal amount;

    /**
     * 提现账号
     */
    @Column(name = "ACCOUNT")
    private String account;

    /**
     * 提现说明
     */
    @Column(name = "REMARK")
    private String remark;

    /**
     * 提现状态
     */
    @Column(name = "STATUS")
    private Integer status;

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
     * 获取用户id
     *
     * @return USER_ID - 用户id
     */
    public Integer getUserId() {

        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {

        this.userId = userId;
    }

    /**
     * 获取提现流水号
     *
     * @return CODE - 提现流水号
     */
    public String getCode() {

        return code;
    }

    /**
     * 设置提现流水号
     *
     * @param code 提现流水号
     */
    public void setCode(String code) {

        this.code = code == null ? null : code.trim();
    }

    /**
     * 获取提现金额
     *
     * @return AMOUNT - 提现金额
     */
    public BigDecimal getAmount() {

        return amount;
    }

    /**
     * 设置提现金额
     *
     * @param amount 提现金额
     */
    public void setAmount(BigDecimal amount) {

        this.amount = amount;
    }

    /**
     * 获取提现说明
     *
     * @return REMARK - 提现说明
     */
    public String getRemark() {

        return remark;
    }

    /**
     * 设置提现说明
     *
     * @param remark 提现说明
     */
    public void setRemark(String remark) {

        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * 获取提现状态
     *
     * @return STATUS - 提现状态
     */
    public Integer getStatus() {

        return status;
    }

    /**
     * 设置提现状态
     *
     * @param status 提现状态
     */
    public void setStatus(Integer status) {

        this.status = status;
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

    public String getAccount() {

        return account;
    }

    public void setAccount(String account) {

        this.account = account;
    }
}