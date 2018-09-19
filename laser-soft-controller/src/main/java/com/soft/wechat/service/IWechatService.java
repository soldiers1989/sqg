package com.soft.wechat.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

@Service
public interface IWechatService {

    public String getAccessTokenCache();

    /**
     * 获取授权请求地址
     * 
     * @return
     */
    public String getWeChartUrl(String redirectUri, String state);

    /**
     * 获取返回授权码
     */
    public String getauthcode(HttpServletRequest request);

    /**
     * 参数
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
     * 删除菜单
     */
    public String deleteMenu();

}
