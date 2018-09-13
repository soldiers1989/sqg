package com.soft.tbk.model;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_coupon")
public class TbkCoupon {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 推广位
     */
    @Column(name = "PID")
    private String pid;

    /**
     * 商品ID
     */
    @Column(name = "ITEM_ID")
    private Long itemId;

    /**
     * 淘口令
     */
    @Column(name = "TKL")
    private String tkl;

    /**
     * 输入的淘口令
     */
    @Column(name = "INPUT_TKL")
    private String inputTkl;

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
     * 预计佣金
     */
    @Column(name = "COMMISSION")
    private BigDecimal commission;

    /**
     * 是否有优惠券
     */
    @Column(name = "COUPON_EXIST")
    private String couponExist;

    /**
     * 优惠券金额
     */
    @Column(name = "COUPON_AMOUT")
    private BigDecimal couponAmout;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private Integer status;

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
     * 获取推广位
     *
     * @return PID - 推广位
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置推广位
     *
     * @param pid 推广位
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取商品ID
     *
     * @return ITEM_ID - 商品ID
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * 设置商品ID
     *
     * @param itemId 商品ID
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * 获取淘口令
     *
     * @return TKL - 淘口令
     */
    public String getTkl() {
        return tkl;
    }

    /**
     * 设置淘口令
     *
     * @param tkl 淘口令
     */
    public void setTkl(String tkl) {
        this.tkl = tkl == null ? null : tkl.trim();
    }

    /**
     * 获取输入的淘口令
     *
     * @return INPUT_TKL - 输入的淘口令
     */
    public String getInputTkl() {
        return inputTkl;
    }

    /**
     * 设置输入的淘口令
     *
     * @param inputTkl 输入的淘口令
     */
    public void setInputTkl(String inputTkl) {
        this.inputTkl = inputTkl == null ? null : inputTkl.trim();
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
     * @param itemImage 商品图片
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
     * @param itemTitle 商品标题
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
     * @param itemPrice 商品价格
     */
    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
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
     * @param commission 预计佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取是否有优惠券
     *
     * @return COUPON_EXIST - 是否有优惠券
     */
    public String getCouponExist() {
        return couponExist;
    }

    /**
     * 设置是否有优惠券
     *
     * @param couponExist 是否有优惠券
     */
    public void setCouponExist(String couponExist) {
        this.couponExist = couponExist == null ? null : couponExist.trim();
    }

    /**
     * 获取优惠券金额
     *
     * @return COUPON_AMOUT - 优惠券金额
     */
    public BigDecimal getCouponAmout() {
        return couponAmout;
    }

    /**
     * 设置优惠券金额
     *
     * @param couponAmout 优惠券金额
     */
    public void setCouponAmout(BigDecimal couponAmout) {
        this.couponAmout = couponAmout;
    }

    /**
     * 获取状态
     *
     * @return STATUS - 状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态
     *
     * @param status 状态
     */
    public void setStatus(Integer status) {
        this.status = status;
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
}