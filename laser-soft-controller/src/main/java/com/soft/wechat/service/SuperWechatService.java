package com.soft.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.soft.tbk.core.cache.IRedisJsonStringService;

public class SuperWechatService {

    /**
     * 永久的字符串参数值
     */
    protected final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

    /**
     * 永久的整型参数值
     */
    protected final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";

    /**
     * 临时的字符串参数值
     */
    protected final static String QR_STR_SCENE = "QR_STR_SCENE";

    protected final static String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    protected final static String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    protected static final int THIRTY_DAY = 2_592_000;// 最长有效期

    protected static final int SCENE_STR_MAX_LENGTH = 64;

    protected final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    protected static final long TOKEN_TIME_OUT = 2 * 60 * 60 * 1000L;

    @Value("${weixin.appID}")
    protected String appid;

    @Value("${weixin.appsecret}")
    protected String secret;

    @Autowired
    protected IRedisJsonStringService redisJsonStringService;

}
