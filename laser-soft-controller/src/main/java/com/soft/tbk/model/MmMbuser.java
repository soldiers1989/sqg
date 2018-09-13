package com.soft.tbk.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mm_mbuser")
public class MmMbuser {
    @Id
    @Column(name = "MBUSER_ID")
    private Integer mbuserId;

    /**
     * 代码
     */
    @Column(name = "MBUSER_CODE")
    private String mbuserCode;

    /**
     * 商户CODE
     */
    @Column(name = "MERBER_CODE")
    private String merberCode;

    /**
     * 登录名
     */
    @Column(name = "MBUSER_NAME")
    private String mbuserName;

    /**
     * 外系统唯一吗
     */
    @Column(name = "MBUSER_EXCODE")
    private String mbuserExcode;

    /**
     * 昵称
     */
    @Column(name = "MBUSER_NICKNAME")
    private String mbuserNickname;

    /**
     * 真实姓名
     */
    @Column(name = "MBUSER_RELNAME")
    private String mbuserRelname;

    /**
     * 邮箱
     */
    @Column(name = "MBUSEREMIAL")
    private String mbuseremial;

    /**
     * 手机
     */
    @Column(name = "MBUSER_PHONE")
    private String mbuserPhone;

    /**
     * QQ
     */
    @Column(name = "MBUSER_QQ")
    private String mbuserQq;

    /**
     * 电话
     */
    @Column(name = "MBUSER_TEL")
    private String mbuserTel;

    /**
     * 登录密码
     */
    @Column(name = "MBUSER_PWSSWD")
    private String mbuserPwsswd;

    /**
     * 支付密码
     */
    @Column(name = "MBUSER_PYPW")
    private String mbuserPypw;

    /**
     * 0 管理员  1 操作员
     */
    @Column(name = "MBUSER_TYPE")
    private Integer mbuserType;

    /**
     * 预留信息
     */
    @Column(name = "MBUSER_MSG")
    private String mbuserMsg;

    /**
     * 头像路径
     */
    @Column(name = "MBUSER_IMGPATH")
    private String mbuserImgpath;

    /**
     * 头像URL
     */
    @Column(name = "MBUSER_IMGURL")
    private String mbuserImgurl;

    /**
     * 有效时间
     */
    @Column(name = "GMT_VALID")
    private Date gmtValid;

    /**
     * 创建时间
     */
    @Column(name = "GMT_CREATE")
    private Date gmtCreate;

    /**
     * 修改时间
     */
    @Column(name = "GMT_MODIFIED")
    private Date gmtModified;

    /**
     * 备注
     */
    @Column(name = "MEMO")
    private String memo;

    /**
     * 状态
     */
    @Column(name = "DATA_STATE")
    private Integer dataState;

    /**
     * 角色代码
     */
    @Column(name = "ROLE_CODE")
    private String roleCode;

    /**
     * 是否是演示账户、1是演示账户
     */
    @Column(name = "DEMO_FLAG")
    private Integer demoFlag;

    /**
     * 租户ID
     */
    @Column(name = "TENANT_CODE")
    private String tenantCode;

    /**
     * 微信用户ID
     */
    @Column(name = "MBUSER_OPENID")
    private String mbuserOpenid;

    /**
     * @return MBUSER_ID
     */
    public Integer getMbuserId() {
        return mbuserId;
    }

    /**
     * @param mbuserId
     */
    public void setMbuserId(Integer mbuserId) {
        this.mbuserId = mbuserId;
    }

    /**
     * 获取代码
     *
     * @return MBUSER_CODE - 代码
     */
    public String getMbuserCode() {
        return mbuserCode;
    }

    /**
     * 设置代码
     *
     * @param mbuserCode 代码
     */
    public void setMbuserCode(String mbuserCode) {
        this.mbuserCode = mbuserCode == null ? null : mbuserCode.trim();
    }

    /**
     * 获取商户CODE
     *
     * @return MERBER_CODE - 商户CODE
     */
    public String getMerberCode() {
        return merberCode;
    }

    /**
     * 设置商户CODE
     *
     * @param merberCode 商户CODE
     */
    public void setMerberCode(String merberCode) {
        this.merberCode = merberCode == null ? null : merberCode.trim();
    }

    /**
     * 获取登录名
     *
     * @return MBUSER_NAME - 登录名
     */
    public String getMbuserName() {
        return mbuserName;
    }

    /**
     * 设置登录名
     *
     * @param mbuserName 登录名
     */
    public void setMbuserName(String mbuserName) {
        this.mbuserName = mbuserName == null ? null : mbuserName.trim();
    }

    /**
     * 获取外系统唯一吗
     *
     * @return MBUSER_EXCODE - 外系统唯一吗
     */
    public String getMbuserExcode() {
        return mbuserExcode;
    }

    /**
     * 设置外系统唯一吗
     *
     * @param mbuserExcode 外系统唯一吗
     */
    public void setMbuserExcode(String mbuserExcode) {
        this.mbuserExcode = mbuserExcode == null ? null : mbuserExcode.trim();
    }

    /**
     * 获取昵称
     *
     * @return MBUSER_NICKNAME - 昵称
     */
    public String getMbuserNickname() {
        return mbuserNickname;
    }

    /**
     * 设置昵称
     *
     * @param mbuserNickname 昵称
     */
    public void setMbuserNickname(String mbuserNickname) {
        this.mbuserNickname = mbuserNickname == null ? null : mbuserNickname.trim();
    }

    /**
     * 获取真实姓名
     *
     * @return MBUSER_RELNAME - 真实姓名
     */
    public String getMbuserRelname() {
        return mbuserRelname;
    }

    /**
     * 设置真实姓名
     *
     * @param mbuserRelname 真实姓名
     */
    public void setMbuserRelname(String mbuserRelname) {
        this.mbuserRelname = mbuserRelname == null ? null : mbuserRelname.trim();
    }

    /**
     * 获取邮箱
     *
     * @return MBUSEREMIAL - 邮箱
     */
    public String getMbuseremial() {
        return mbuseremial;
    }

    /**
     * 设置邮箱
     *
     * @param mbuseremial 邮箱
     */
    public void setMbuseremial(String mbuseremial) {
        this.mbuseremial = mbuseremial == null ? null : mbuseremial.trim();
    }

    /**
     * 获取手机
     *
     * @return MBUSER_PHONE - 手机
     */
    public String getMbuserPhone() {
        return mbuserPhone;
    }

    /**
     * 设置手机
     *
     * @param mbuserPhone 手机
     */
    public void setMbuserPhone(String mbuserPhone) {
        this.mbuserPhone = mbuserPhone == null ? null : mbuserPhone.trim();
    }

    /**
     * 获取QQ
     *
     * @return MBUSER_QQ - QQ
     */
    public String getMbuserQq() {
        return mbuserQq;
    }

    /**
     * 设置QQ
     *
     * @param mbuserQq QQ
     */
    public void setMbuserQq(String mbuserQq) {
        this.mbuserQq = mbuserQq == null ? null : mbuserQq.trim();
    }

    /**
     * 获取电话
     *
     * @return MBUSER_TEL - 电话
     */
    public String getMbuserTel() {
        return mbuserTel;
    }

    /**
     * 设置电话
     *
     * @param mbuserTel 电话
     */
    public void setMbuserTel(String mbuserTel) {
        this.mbuserTel = mbuserTel == null ? null : mbuserTel.trim();
    }

    /**
     * 获取登录密码
     *
     * @return MBUSER_PWSSWD - 登录密码
     */
    public String getMbuserPwsswd() {
        return mbuserPwsswd;
    }

    /**
     * 设置登录密码
     *
     * @param mbuserPwsswd 登录密码
     */
    public void setMbuserPwsswd(String mbuserPwsswd) {
        this.mbuserPwsswd = mbuserPwsswd == null ? null : mbuserPwsswd.trim();
    }

    /**
     * 获取支付密码
     *
     * @return MBUSER_PYPW - 支付密码
     */
    public String getMbuserPypw() {
        return mbuserPypw;
    }

    /**
     * 设置支付密码
     *
     * @param mbuserPypw 支付密码
     */
    public void setMbuserPypw(String mbuserPypw) {
        this.mbuserPypw = mbuserPypw == null ? null : mbuserPypw.trim();
    }

    /**
     * 获取0 管理员  1 操作员
     *
     * @return MBUSER_TYPE - 0 管理员  1 操作员
     */
    public Integer getMbuserType() {
        return mbuserType;
    }

    /**
     * 设置0 管理员  1 操作员
     *
     * @param mbuserType 0 管理员  1 操作员
     */
    public void setMbuserType(Integer mbuserType) {
        this.mbuserType = mbuserType;
    }

    /**
     * 获取预留信息
     *
     * @return MBUSER_MSG - 预留信息
     */
    public String getMbuserMsg() {
        return mbuserMsg;
    }

    /**
     * 设置预留信息
     *
     * @param mbuserMsg 预留信息
     */
    public void setMbuserMsg(String mbuserMsg) {
        this.mbuserMsg = mbuserMsg == null ? null : mbuserMsg.trim();
    }

    /**
     * 获取头像路径
     *
     * @return MBUSER_IMGPATH - 头像路径
     */
    public String getMbuserImgpath() {
        return mbuserImgpath;
    }

    /**
     * 设置头像路径
     *
     * @param mbuserImgpath 头像路径
     */
    public void setMbuserImgpath(String mbuserImgpath) {
        this.mbuserImgpath = mbuserImgpath == null ? null : mbuserImgpath.trim();
    }

    /**
     * 获取头像URL
     *
     * @return MBUSER_IMGURL - 头像URL
     */
    public String getMbuserImgurl() {
        return mbuserImgurl;
    }

    /**
     * 设置头像URL
     *
     * @param mbuserImgurl 头像URL
     */
    public void setMbuserImgurl(String mbuserImgurl) {
        this.mbuserImgurl = mbuserImgurl == null ? null : mbuserImgurl.trim();
    }

    /**
     * 获取有效时间
     *
     * @return GMT_VALID - 有效时间
     */
    public Date getGmtValid() {
        return gmtValid;
    }

    /**
     * 设置有效时间
     *
     * @param gmtValid 有效时间
     */
    public void setGmtValid(Date gmtValid) {
        this.gmtValid = gmtValid;
    }

    /**
     * 获取创建时间
     *
     * @return GMT_CREATE - 创建时间
     */
    public Date getGmtCreate() {
        return gmtCreate;
    }

    /**
     * 设置创建时间
     *
     * @param gmtCreate 创建时间
     */
    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * 获取修改时间
     *
     * @return GMT_MODIFIED - 修改时间
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * 设置修改时间
     *
     * @param gmtModified 修改时间
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
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
     * @return DATA_STATE - 状态
     */
    public Integer getDataState() {
        return dataState;
    }

    /**
     * 设置状态
     *
     * @param dataState 状态
     */
    public void setDataState(Integer dataState) {
        this.dataState = dataState;
    }

    /**
     * 获取角色代码
     *
     * @return ROLE_CODE - 角色代码
     */
    public String getRoleCode() {
        return roleCode;
    }

    /**
     * 设置角色代码
     *
     * @param roleCode 角色代码
     */
    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode == null ? null : roleCode.trim();
    }

    /**
     * 获取是否是演示账户、1是演示账户
     *
     * @return DEMO_FLAG - 是否是演示账户、1是演示账户
     */
    public Integer getDemoFlag() {
        return demoFlag;
    }

    /**
     * 设置是否是演示账户、1是演示账户
     *
     * @param demoFlag 是否是演示账户、1是演示账户
     */
    public void setDemoFlag(Integer demoFlag) {
        this.demoFlag = demoFlag;
    }

    /**
     * 获取租户ID
     *
     * @return TENANT_CODE - 租户ID
     */
    public String getTenantCode() {
        return tenantCode;
    }

    /**
     * 设置租户ID
     *
     * @param tenantCode 租户ID
     */
    public void setTenantCode(String tenantCode) {
        this.tenantCode = tenantCode == null ? null : tenantCode.trim();
    }

    /**
     * 获取微信用户ID
     *
     * @return MBUSER_OPENID - 微信用户ID
     */
    public String getMbuserOpenid() {
        return mbuserOpenid;
    }

    /**
     * 设置微信用户ID
     *
     * @param mbuserOpenid 微信用户ID
     */
    public void setMbuserOpenid(String mbuserOpenid) {
        this.mbuserOpenid = mbuserOpenid == null ? null : mbuserOpenid.trim();
    }
}