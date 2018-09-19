package com.soft.wechat.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.core.cache.CacheSync;
import com.soft.wechat.dto.WxQrcodeActionDTO;
import com.soft.wechat.model.WxQrcode;
import com.soft.wechat.service.IWechatService;
import com.soft.wechat.service.SuperWechatService;
import com.soft.wechat.util.WebUtils;

@Service
public class WechatServiceImpl extends SuperWechatService implements IWechatService {

    Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

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

    /**
     * 创建二维码
     *
     * @param sceneStr
     *            场景值字符串ID 长度限制为1到64
     * @return
     */
    public String createQrcode(String sceneStr) {

        // 场景数据
        String actionInfo = null;
        if (StringUtils.isNotBlank(sceneStr)) {
            if (sceneStr.length() > SCENE_STR_MAX_LENGTH) {
                throw new IllegalArgumentException("sceneStr length more than 64");
            }
            actionInfo = String.format("{\"scene\":{\"scene_str\":\"%s\"}}", sceneStr);
        }
        WxQrcodeActionDTO qrcodeActionDTO = new WxQrcodeActionDTO();
        qrcodeActionDTO.setExpire_seconds(THIRTY_DAY);
        qrcodeActionDTO.setAction_name(QR_STR_SCENE);
        qrcodeActionDTO.setAction_info(actionInfo);
        String url = QRCODE_CREATE_URL + getAccessTokenCache();
        try {
            String json = qrcodeActionDTO.toString();
            WxQrcode wxQrcode = WebUtils.postForObject(url, json, WxQrcode.class, 0, 0);
            if (wxQrcode == null) {
                return null;
            }
            return QRCODE_SHOW + wxQrcode.getTicket();
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
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

    @Override
    public String createMenu(String menuJson) {

        try {
            String accessToken = getAccessTokenCache();
            return WebUtils.postForObject(MENU_CREATE_URL + accessToken, menuJson, String.class, 0, 0);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public String deleteMenu() {

        try {
            String accessToken = getAccessTokenCache();
            return WebUtils.doGet(MENU_DELETE_URL + accessToken, null);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
