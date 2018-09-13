package com.soft.tbk.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_pid")
public class TbkPid {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * PID
     */
    @Column(name = "PID")
    private String pid;

    /**
     * 媒体ID
     */
    @Column(name = "SITE_ID")
    private Integer siteId;

    /**
     * 广告位ID
     */
    @Column(name = "ADZONE_ID")
    private Integer adzoneId;

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
     * @param pid PID
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取媒体ID
     *
     * @return SITE_ID - 媒体ID
     */
    public Integer getSiteId() {
        return siteId;
    }

    /**
     * 设置媒体ID
     *
     * @param siteId 媒体ID
     */
    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }

    /**
     * 获取广告位ID
     *
     * @return ADZONE_ID - 广告位ID
     */
    public Integer getAdzoneId() {
        return adzoneId;
    }

    /**
     * 设置广告位ID
     *
     * @param adzoneId 广告位ID
     */
    public void setAdzoneId(Integer adzoneId) {
        this.adzoneId = adzoneId;
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