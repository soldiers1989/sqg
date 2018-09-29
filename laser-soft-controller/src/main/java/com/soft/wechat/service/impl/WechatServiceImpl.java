package com.soft.wechat.service.impl;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
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
import com.soft.tbk.model.TbkUser;
import com.soft.tbk.service.TbkUserService;
import com.soft.tbk.utils.BeanUtils;
import com.soft.tbk.utils.UuidUtil;
import com.soft.wechat.domain.WeChartOpenIDBean;
import com.soft.wechat.domain.WeChartUserInfoBean;
import com.soft.wechat.domain.WechatMsgTemplateDomain;
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

            String redirectUrl = domainName + contextPath + url;
            String code = getauthcode(request);
            if (StringUtils.isBlank(code)) {
                response.sendRedirect(getWeChartUrl(redirectUrl));
            } else {
                UserSession user = getUserinfo(code);
                if (user != null) {
                    TbkUser tbkUser = tbkUserService.saveTbkUserWithOpenId(user);
                    response.sendRedirect(redirectUrl);
                    try {
                        BeanUtils.copyAllPropertysNotNull(user, tbkUser);
                    } catch (Exception e) {}
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
        String core = ACCESS_TOKEN;
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
            //String json = WebUtils.doGet(token_url + "&appid=wx3098f584368e8667&secret=58484bf41fbecf3a11541eadb50a5d22", null);
            String json = WebUtils.doGet(token_url + "&appid=" + appid + "&secret=" + secret, null);
            Map<String, Object> map = (Map<String, Object>) JSON.parse(json);
            if (map != null) {
                if (map.containsKey("errmsg")) {
                    return null;
                }
                if (map.containsKey(ACCESS_TOKEN)) {
                    return (String) map.get(ACCESS_TOKEN);
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

    /**
     * 创建永久二维码
     *
     * @param sceneStr
     *            场景值字符串ID 长度限制为1到64
     * @return
     */
    public String createLimitQrcode(String sceneStr) {

        // 场景数据
        String actionInfo = null;
        if (StringUtils.isNotBlank(sceneStr)) {
            if (sceneStr.length() > SCENE_STR_MAX_LENGTH) {
                throw new IllegalArgumentException("sceneStr length more than 64");
            }
            actionInfo = String.format("{\"scene\":{\"scene_str\":\"%s\"}}", sceneStr);
        }
        WxQrcodeDomain qrcodeActionDTO = new WxQrcodeDomain();
        qrcodeActionDTO.setAction_name(QR_LIMIT_STR_SCENE);
        qrcodeActionDTO.setAction_info(actionInfo);
        String url = QRCODE_CREATE_URL + getAccessTokenCache();
        try {
            String json = qrcodeActionDTO.toLimitString();
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
    public String getMenu() {

        try {
            String accessToken = getAccessTokenCache();
            return WebUtils.doGet(MENU_GET_URL + accessToken, null);
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

    @Override
    public String sendMessage(WechatMsgTemplateDomain wechatMsgTemplateDomain) {

        if (wechatMsgTemplateDomain == null)
            return null;
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("touser", wechatMsgTemplateDomain.getUserOpenId());
        paramMap.put("template_id", templateId);

        String nikeName = wechatMsgTemplateDomain.getUserNikeName();
        Map<String, Object> dataMap = new HashMap<>();
        String title = "跟踪到您的一个新订单";
        title = StringUtils.isBlank(nikeName) ? title : nikeName + "," + title;
        dataMap.put("first", getMsgMap(title, "#173177"));
        dataMap.put("keyword1", getMsgMap(wechatMsgTemplateDomain.getTradeId(), "#173177"));
        dataMap.put("keyword2", getMsgMap(wechatMsgTemplateDomain.getItemPrice(), "#173177"));
        dataMap.put("keyword3", getMsgMap(wechatMsgTemplateDomain.getCommission(), "#173177"));
        dataMap.put("remark", getMsgMap("点击查看详情", null));
        paramMap.put("data", dataMap);
        try {
            return WebUtils.doPostByJson(SEND_MESSAGE_URL + getAccessTokenCache(), paramMap, 0, 0);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    private Map<String, Object> getMsgMap(Object value, Object color) {

        Map<String, Object> map = new HashMap<>();
        map.put("value", value);
        map.put("color", color);
        return map;
    }

    @Override
    public String uploadMedia(String type, String filePath) {

        BufferedReader reader = null;
        String result = null;
        try {
            String url = UPLOAD_MEDIA_URL + getAccessTokenCache() + "&type=" + type;
            URL urlObj = new URL(url);
            //连接
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setUseCaches(false);
            //设置请求头信息
            con.setRequestProperty("Connection", "Keep-Alive");
            con.setRequestProperty("Charset", "UTF-8");
            //设置边界
            String BOUNDARY = "----------" + System.currentTimeMillis();
            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
            StringBuilder sb = new StringBuilder();
            sb.append("--");
            sb.append(BOUNDARY);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + UuidUtil.getUuid() + ".jpg\"\r\n");
            sb.append("Content-Type:application/octet-stream\r\n\r\n");
            byte[] head = sb.toString().getBytes("utf-8");
            //获得输出流
            OutputStream out = new DataOutputStream(con.getOutputStream());
            //输出表头
            out.write(head);
            //文件正文部分
            URL fileurl = new URL(filePath); // 打开连接       
            URLConnection filecon = fileurl.openConnection(); // 输入流      
            InputStream is = filecon.getInputStream(); //     
            //把文件已流文件的方式 推入到url中
            DataInputStream in = new DataInputStream(is);
            int bytes = 0;
            byte[] bufferOut = new byte[1024];
            while ((bytes = in.read(bufferOut)) != -1) {
                out.write(bufferOut, 0, bytes);
            }
            in.close();
            //结尾部分
            byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线
            out.write(foot);
            out.flush();
            out.close();
            StringBuffer buffer = new StringBuffer();
            //定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        logger.info("上传图片返回结果：" + result);
        return result;
    }

    @Override
    public String batchgetMaterial(String type, String offset, String count) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("type", type);
        paramMap.put("offset", offset);
        paramMap.put("count", count);
        try {
            return WebUtils.doPostByJson(BATCHGET_MATERIAL_URL + getAccessTokenCache(), paramMap, 0, 0);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {

        WechatServiceImpl action = new WechatServiceImpl();
        String a = action.uploadMedia("image",
                        "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=gQFv8DwAAAAAAAAAAS5odHRwOi8vd2VpeGluLnFxLmNvbS9xLzAyMmNCMXNHam5mUGsxUkRNbDFyY3MAAgRn461bAwQAjScA");
        System.out.println(a);
        /*String userOpenId = "o01fz1P6fq6U7HO5kBNY7PK2Fsi0";
        String templateId = "Do6UCWQMSiRjJu21KNCdwLZ9QZ7zDxRChlLP30ZSxmc";
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("touser", userOpenId);
        paramMap.put("template_id", templateId);
        paramMap.put("url", "http://www.baidu.com");
        
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("first", new WechatServiceImpl().getMsgMap("小调皮,跟踪到您的一个新订单", "#173177"));
        dataMap.put("keyword1", new WechatServiceImpl().getMsgMap("123456678", "#173177"));
        dataMap.put("keyword2", new WechatServiceImpl().getMsgMap("30", "#173177"));
        dataMap.put("keyword3", new WechatServiceImpl().getMsgMap("1.02", "#173177"));
        dataMap.put("remark", new WechatServiceImpl().getMsgMap("点击查看详情", null));
        paramMap.put("data", dataMap);
        System.out.println(JSONObject.toJSONString(paramMap));
        try {
            String a = WebUtils.doPostByJson(SEND_MESSAGE_URL + new WechatServiceImpl().getAccessToken(), paramMap, 0, 0);
            System.out.println(a);
        } catch (IOException e) {}*/
    }

}
