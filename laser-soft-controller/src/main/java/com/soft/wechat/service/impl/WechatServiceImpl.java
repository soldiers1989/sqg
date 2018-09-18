package com.soft.wechat.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.core.cache.CacheSync;
import com.soft.wechat.service.IWechatService;
import com.soft.wechat.service.SuperWechatService;
import com.soft.wechat.util.WebUtils;

@Service
public class WechatServiceImpl extends SuperWechatService implements IWechatService {

    /**
     * 微信 access_token
     * 缓存保存，如果没有请求
     * https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
     * 
     * @return access_token
     */
    public String getAccessTokenCache() {

        //读取缓存
        String core = "access_token";
        return redisJsonStringService.getObject(core, new CacheSync<String>() {

            @Override
            public String invoke() {

                return getAccessToken();
            }

            @Override
            public Class<?> getGenericClass() {

                return String.class;
            }

        }, TOKEN_TIME_OUT);
    }

    @SuppressWarnings("unchecked")
    private String getAccessToken() {

        try {
            String json = WebUtils.doGet(token_url + "&appid=" + appid + "&secret=" + secret, null);
            Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
            if (map != null) {
                if (map.containsKey("errmsg")) {
                    return null;
                }
                if (map.containsKey("access_token")) {
                    return (String) map.get("access_token");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getWeChartUrl(String redirectUri, String state) {

        return null;
    }

    @Override
    public String getauthcode(HttpServletRequest request) {

        return null;
    }

}
