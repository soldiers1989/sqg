package com.soft.tbk.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_tbk_user")
public class TbkUser {
    @Id
    @Column(name = "ID")
    private Integer id;

    /**
     * 父级代码
     */
    @Column(name = "PARENT_ID")
    private Integer parentId;

    /**
     * 父级代码PATH，以逗号隔开
     */
    @Column(name = "PARENT_ID_PATH")
    private String parentIdPath;

    /**
     * 昵称
     */
    @Column(name = "USER_NICKNAME")
    private String userNickname;

    /**
     * 真实姓名
     */
    @Column(name = "USER_RELNAME")
    private String userRelname;

    /**
     * 邮箱
     */
    @Column(name = "USER_EMIAL")
    private String userEmial;

    /**
     * 手机
     */
    @Column(name = "USER_PHONE")
    private String userPhone;

    /**
     * QQ
     */
    @Column(name = "USER_QQ")
    private String userQq;

    /**
     * 登录密码
     */
    @Column(name = "USER_PWSSWD")
    private String userPwsswd;

    /**
     * 支付密码
     */
    @Column(name = "USER_PYPW")
    private String userPypw;

    /**
     * 用户类型
     */
    @Column(name = "USER_TYPE")
    private Integer userType;

    /**
     * 预留信息
     */
    @Column(name = "USER_MSG")
    private String userMsg;

    /**
     * 用户级别
     */
    @Column(name = "USER_LEVEL")
    private String userLevel;

    /**
     * 头像URL
     */
    @Column(name = "USER_IMGURL")
    private String userImgurl;

    /**
     * 备注
     */
    @Column(name = "MEMO")
    private String memo;

    /**
     * 状态
     */
    @Column(name = "STATUS")
    private Integer status;

    /**
     * 微信用户ID
     */
    @Column(name = "USER_OPENID")
    private String userOpenid;

    /**
     * 支付宝账号
     */
    @Column(name = "USER_ALIPAY_ACCOUNT")
    private String userAlipayAccount;

    /**
     * 是否代理
     */
    @Column(name = "AGENT_FLAG")
    private Integer agentFlag;

    /**
     * 代理地区
     */
    @Column(name = "AGENT_AREA")
    private String agentArea;

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
     * 获取父级代码
     *
     * @return PARENT_ID - 父级代码
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * 设置父级代码
     *
     * @param parentId 父级代码
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取父级代码PATH，以逗号隔开
     *
     * @return PARENT_ID_PATH - 父级代码PATH，以逗号隔开
     */
    public String getParentIdPath() {
        return parentIdPath;
    }

    /**
     * 设置父级代码PATH，以逗号隔开
     *
     * @param parentIdPath 父级代码PATH，以逗号隔开
     */
    public void setParentIdPath(String parentIdPath) {
        this.parentIdPath = parentIdPath == null ? null : parentIdPath.trim();
    }

    /**
     * 获取昵称
     *
     * @return USER_NICKNAME - 昵称
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 设置昵称
     *
     * @param userNickname 昵称
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return USER_RELNAME - 真实姓名
     */
    public String getUserRelname() {
        return userRelname;
    }

    /**
     * 设置真实姓名
     *
     * @param userRelname 真实姓名
     */
    public void setUserRelname(String userRelname) {
        this.userRelname = userRelname == null ? null : userRelname.trim();
    }

    /**
     * 获取邮箱
     *
     * @return USER_EMIAL - 邮箱
     */
    public String getUserEmial() {
        return userEmial;
    }

    /**
     * 设置邮箱
     *
     * @param userEmial 邮箱
     */
    public void setUserEmial(String userEmial) {
        this.userEmial = userEmial == null ? null : userEmial.trim();
    }

    /**
     * 获取手机
     *
     * @return USER_PHONE - 手机
     */
    public String getUserPhone() {
        return userPhone;
    }

    /**
     * 设置手机
     *
     * @param userPhone 手机
     */
    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    /**
     * 获取QQ
     *
     * @return USER_QQ - QQ
     */
    public String getUserQq() {
        return userQq;
    }

    /**
     * 设置QQ
     *
     * @param userQq QQ
     */
    public void setUserQq(String userQq) {
        this.userQq = userQq == null ? null : userQq.trim();
    }

    /**
     * 获取登录密码
     *
     * @return USER_PWSSWD - 登录密码
     */
    public String getUserPwsswd() {
        return userPwsswd;
    }

    /**
     * 设置登录密码
     *
     * @param userPwsswd 登录密码
     */
    public void setUserPwsswd(String userPwsswd) {
        this.userPwsswd = userPwsswd == null ? null : userPwsswd.trim();
    }

    /**
     * 获取支付密码
     *
     * @return USER_PYPW - 支付密码
     */
    public String getUserPypw() {
        return userPypw;
    }

    /**
     * 设置支付密码
     *
     * @param userPypw 支付密码
     */
    public void setUserPypw(String userPypw) {
        this.userPypw = userPypw == null ? null : userPypw.trim();
    }

    /**
     * 获取用户类型
     *
     * @return USER_TYPE - 用户类型
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置用户类型
     *
     * @param userType 用户类型
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取预留信息
     *
     * @return USER_MSG - 预留信息
     */
    public String getUserMsg() {
        return userMsg;
    }

    /**
     * 设置预留信息
     *
     * @param userMsg 预留信息
     */
    public void setUserMsg(String userMsg) {
        this.userMsg = userMsg == null ? null : userMsg.trim();
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
     * 获取头像URL
     *
     * @return USER_IMGURL - 头像URL
     */
    public String getUserImgurl() {
        return userImgurl;
    }

    /**
     * 设置头像URL
     *
     * @param userImgurl 头像URL
     */
    public void setUserImgurl(String userImgurl) {
        this.userImgurl = userImgurl == null ? null : userImgurl.trim();
    }

    /**
     * 获取备注
     *
     * @return MEMO - 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 设置备注
     *
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
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
     * 获取微信用户ID
     *
     * @return USER_OPENID - 微信用户ID
     */
    public String getUserOpenid() {
        return userOpenid;
    }

    /**
     * 设置微信用户ID
     *
     * @param userOpenid 微信用户ID
     */
    public void setUserOpenid(String userOpenid) {
        this.userOpenid = userOpenid == null ? null : userOpenid.trim();
    }

    /**
     * 获取支付宝账号
     *
     * @return USER_ALIPAY_ACCOUNT - 支付宝账号
     */
    public String getUserAlipayAccount() {
        return userAlipayAccount;
    }

    /**
     * 设置支付宝账号
     *
     * @param userAlipayAccount 支付宝账号
     */
    public void setUserAlipayAccount(String userAlipayAccount) {
        this.userAlipayAccount = userAlipayAccount == null ? null : userAlipayAccount.trim();
    }

    /**
     * 获取是否代理
     *
     * @return AGENT_FLAG - 是否代理
     */
    public Integer getAgentFlag() {
        return agentFlag;
    }

    /**
     * 设置是否代理
     *
     * @param agentFlag 是否代理
     */
    public void setAgentFlag(Integer agentFlag) {
        this.agentFlag = agentFlag;
    }

    /**
     * 获取代理地区
     *
     * @return AGENT_AREA - 代理地区
     */
    public String getAgentArea() {
        return agentArea;
    }

    /**
     * 设置代理地区
     *
     * @param agentArea 代理地区
     */
    public void setAgentArea(String agentArea) {
        this.agentArea = agentArea == null ? null : agentArea.trim();
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