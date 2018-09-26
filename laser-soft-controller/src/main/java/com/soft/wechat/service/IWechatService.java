package com.soft.wechat.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

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
    public String sendMessage(Map<String, String> messageMap);
}
