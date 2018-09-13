package com.soft.tbk.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_pid_item")
public class TbkPidItem {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 商品ID
     */
    @Column(name = "ITEM_ID")
    private Long itemId;

    /**
     * 推广位ID
     */
    @Column(name = "PID")
    private String pid;

    /**
     * 下一个可用推广位
     */
    @Column(name = "NEXT_PID")
    private String nextPid;

    /**
     * 用户ID
     */
    @Column(name = "USER_ID")
    private Integer userId;

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
     * 获取推广位ID
     *
     * @return PID - 推广位ID
     */
    public String getPid() {
        return pid;
    }

    /**
     * 设置推广位ID
     *
     * @param pid 推广位ID
     */
    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    /**
     * 获取下一个可用推广位
     *
     * @return NEXT_PID - 下一个可用推广位
     */
    public String getNextPid() {
        return nextPid;
    }

    /**
     * 设置下一个可用推广位
     *
     * @param nextPid 下一个可用推广位
     */
    public void setNextPid(String nextPid) {
        this.nextPid = nextPid == null ? null : nextPid.trim();
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