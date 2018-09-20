package com.soft.wechat.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.soft.tbk.core.cache.CacheSync;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.service.TbkUserService;
import com.soft.wechat.domain.WeChartOpenIDBean;
import com.soft.wechat.domain.WeChartUserInfoBean;
import com.soft.wechat.domain.WxQrcodeDomain;
import com.soft.wechat.model.WxQrcode;
import com.soft.wechat.service.IWechatService;
import com.soft.wechat.service.SuperWechatService;
import com.soft.wechat.util.JsonUtil;
import com.soft.wechat.util.WebUtils;
import com.soft.wechat.validator.ExpressionValidator;

@Service
public class WechatServiceImpl extends SuperWechatService implements IWechatService {

    Logger logger = LoggerFactory.getLogger(WechatServiceImpl.class);

    @Autowired
    TbkUserService tbkUserService;

    /**
     * 微信授权登录
     */
    public boolean requestAuth(HttpServletRequest request, HttpServletResponse response, String contextPath, String url) {

        try {
            String code = getauthcode(request);
            if (StringUtils.isBlank(code)) {
                response.sendRedirect(getWeChartUrl(domainName + contextPath + url));
            } else {
                UserSession user = getUserinfo(code);
                if (user != null) {
                    tbkUserService.saveTbkUserWithOpenId(user);
                    response.sendRedirect(domainName + contextPath + url);
                    request.getSession().setAttribute("userSession", user);
                    return false;
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }

    /**
     * 微信 access_token(全局token)
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
        WxQrcodeDomain qrcodeActionDTO = new WxQrcodeDomain();
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

    private String getWeChartUrl(String redirectUri) {

        String redirectUriEncoded = "";
        try {
            redirectUriEncoded = URLEncoder.encode(redirectUri, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        StringBuffer sb = new StringBuffer();
        sb.append(oauth_token_url).append("?").append("appid=").append(appid);
        sb.append("&redirect_uri=").append(redirectUriEncoded);
        sb.append("&response_type=").append("code");
        sb.append("&scope=").append("snsapi_base");
        sb.append("&state=").append(getName());
        sb.append("#wechat_redirect");
        return sb.toString();
    }

    private String getauthcode(HttpServletRequest request) {

        if (StringUtils.isNotBlank(request.getParameter("state")) && request.getParameter("state").equals(getName())) {
            return request.getParameter("code");
        }
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

    private String getName() {

        return "wx";
    }

    /**
     * 获取openid
     */
    private String getWeChartOpenId(String code) {

        String url = sns_token_url + "?appid=" + appid + "&secret=" + secret + "&code=" + code + "&grant_type=authorization_code";
        String json = "";
        try {
            json = WebUtils.doGet(url, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

    private UserSession getUserinfo(String code) {

        // 获取secret
        String json = getWeChartOpenId(code);
        if (json.indexOf("errcode") == -1) {
            WeChartOpenIDBean open = JsonUtil.buildNonNullBinder().getJsonToObject(json, WeChartOpenIDBean.class);
            if (open != null) {
                UserSession user = new UserSession();
                String userinfojson = getWeChartUserinfo(open.getOpenid());
                WeChartUserInfoBean userinfo = JsonUtil.buildNonNullBinder().getJsonToObject(userinfojson, WeChartUserInfoBean.class);
                if (userinfo != null && userinfo.getNickname() != null) {
                    user.setUserImgurl(userinfo.getHeadimgurl());
                    user.setUserNickname(renameNick(userinfo.getNickname(), open.getOpenid()));
                    user.setSex(userinfo.getSex());
                }
                user.setUserOpenid(open.getOpenid());
                return user;
            }
        }
        return null;
    }

    /**
     * 获取用户信息
     * 
     * @param openid
     */
    private String getWeChartUserinfo(String openid) {

        String url = userinfo_url + "?access_token=" + getAccessTokenCache() + "&openid=" + openid + "&lang=zh_CN";
        String json = "";
        try {
            json = WebUtils.doGet(url, null);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return json;
    }

    @SuppressWarnings("static-access")
    public String renameNick(String nickName, String openId) {

        if (!new ExpressionValidator().containsEmoji(nickName)) {
            return nickName;
        } else {
            return openId.substring(openId.length() - 10);
        }
    }
}
