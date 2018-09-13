package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_commission")
public class TbkCommission {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 订单ID
     */
    @Column(name = "ORDER_ID")
    private Integer orderId;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 关联用户ID
     */
    @Column(name = "RELATION_USER_ID")
    private Integer relationUserId;

    /**
     * 佣金
     */
    @Column(name = "COMMISSION")
    private BigDecimal commission;

    /**
     * 创建时间
     */
    @Column(name = "CREATE_TIME")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    /**
     * 佣金类型(自己佣金/团队佣金)
     */
    @Column(name = "COMMISSION_TYPE")
    private String commissionType;

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
     * 获取订单ID
     *
     * @return ORDER_ID - 订单ID
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * 设置订单ID
     *
     * @param orderId 订单ID
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
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
     * @param userId 用户ID
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取关联用户ID
     *
     * @return RELATION_USER_ID - 关联用户ID
     */
    public Integer getRelationUserId() {
        return relationUserId;
    }

    /**
     * 设置关联用户ID
     *
     * @param relationUserId 关联用户ID
     */
    public void setRelationUserId(Integer relationUserId) {
        this.relationUserId = relationUserId;
    }

    /**
     * 获取佣金
     *
     * @return COMMISSION - 佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置佣金
     *
     * @param commission 佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
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
     * 获取佣金类型(自己佣金/团队佣金)
     *
     * @return COMMISSION_TYPE - 佣金类型(自己佣金/团队佣金)
     */
    public String getCommissionType() {
        return commissionType;
    }

    /**
     * 设置佣金类型(自己佣金/团队佣金)
     *
     * @param commissionType 佣金类型(自己佣金/团队佣金)
     */
    public void setCommissionType(String commissionType) {
        this.commissionType = commissionType == null ? null : commissionType.trim();
    }
}