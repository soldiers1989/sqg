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
    String getWeChartUrl(String redirectUri, String state);

    /**
     * 获取返回授权码
     */
    String getauthcode(HttpServletRequest request);
}
