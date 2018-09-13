package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_rate")
public class TbkRate {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 用户级别
     */
    @Column(name = "USER_LEVEL")
    private String userLevel;

    /**
     * 分佣比例
     */
    @Column(name = "RATE_VALUE")
    private BigDecimal rateValue;

    /**
     * 佣金级别(1级，2级)
     */
    @Column(name = "RATE_LEVEL")
    private Integer rateLevel;

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
     * 获取用户级别
     *
     * @return USER_LEVEL - 用户级别
     */
    public String getUserLevel() {
        return userLevel;
    }

    /**
     * 设置用户级别
     *
     * @param userLevel 用户级别
     */
    public void setUserLevel(String userLevel) {
        this.userLevel = userLevel == null ? null : userLevel.trim();
    }

    /**
     * 获取分佣比例
     *
     * @return RATE_VALUE - 分佣比例
     */
    public BigDecimal getRateValue() {
        return rateValue;
    }

    /**
     * 设置分佣比例
     *
     * @param rateValue 分佣比例
     */
    public void setRateValue(BigDecimal rateValue) {
        this.rateValue = rateValue;
    }

    /**
     * 获取佣金级别(1级，2级)
     *
     * @return RATE_LEVEL - 佣金级别(1级，2级)
     */
    public Integer getRateLevel() {
        return rateLevel;
    }

    /**
     * 设置佣金级别(1级，2级)
     *
     * @param rateLevel 佣金级别(1级，2级)
     */
    public void setRateLevel(Integer rateLevel) {
        this.rateLevel = rateLevel;
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