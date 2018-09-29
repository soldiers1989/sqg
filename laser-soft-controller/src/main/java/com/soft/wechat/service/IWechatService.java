package com.soft.wechat.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import com.soft.wechat.domain.WechatMsgTemplateDomain;

@Service
public interface IWechatService {

    /**
     * 获取全局token
     * 
     * @return
     */
    public String getAccessTokenCache();

    /**
     * 微信授权登录
     */
    public boolean requestAuth(HttpServletRequest request, HttpServletResponse response, String contextPath, String url);

    /**
     * 创建二维码
     * 
     * @param code
     * @return
     */
    public String createQrcode(String code);

    /**
     * 创建永久二维码
     * 
     * @param code
     * @return
     */
    public String createLimitQrcode(String code);

    /**
     * 新增临时素材
     * 
     * @param type 图片（image）、语音（voice）、视频（video）和缩略图（thumb）
     * @return
     */
    public String uploadMedia(String type, String filePath);

    /**
     * 创建菜单
     */
    public String createMenu(String menuJson);

    /**
     * 查询菜单
     */
    public String getMenu();

    /**
     * 删除菜单
     */
    public String deleteMenu();

    /**
     * 发送模板消息
     */
    public String sendMessage(WechatMsgTemplateDomain wechatMsgTemplateDomain);

    /**
     * 获取素材列表
     * 
     * @return
     */
    public String batchgetMaterial(String type, String offset, String count);
}
