package com.soft.tbk.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_qrcode")
public class TbkQrcode {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private Integer userId;

    /**
     * 二维码
     */
    @Column(name = "QR_URL")
    private String qrUrl;

    /**
     * 长期、短期
     */
    @Column(name = "QR_TYPE")
    private String qrType;

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
     * 获取二维码
     *
     * @return QR_URL - 二维码
     */
    public String getQrUrl() {
        return qrUrl;
    }

    /**
     * 设置二维码
     *
     * @param qrUrl 二维码
     */
    public void setQrUrl(String qrUrl) {
        this.qrUrl = qrUrl == null ? null : qrUrl.trim();
    }

    /**
     * 获取长期、短期
     *
     * @return QR_TYPE - 长期、短期
     */
    public String getQrType() {
        return qrType;
    }

    /**
     * 设置长期、短期
     *
     * @param qrType 长期、短期
     */
    public void setQrType(String qrType) {
        this.qrType = qrType == null ? null : qrType.trim();
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