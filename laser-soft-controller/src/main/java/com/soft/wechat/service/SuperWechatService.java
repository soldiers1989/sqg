package com.soft.wechat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.soft.tbk.core.cache.IRedisJsonStringService;

public class SuperWechatService {

    protected final static String ACCESS_TOKEN = "access_token";

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

    /**
     * 生成二维码
     */
    protected final static String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    /**
     * 获取二维码
     */
    protected final static String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    protected static final int THIRTY_DAY = 2_592_000;// 最长有效期

    protected static final int SCENE_STR_MAX_LENGTH = 64;

    // 授权登录连接
    protected final static String oauth_token_url = "https://open.weixin.qq.com/connect/oauth2/authorize";

    // 授权登录token获取
    protected final static String sns_token_url = "https://api.weixin.qq.com/sns/oauth2/access_token";

    // 获取用户信息
    protected final static String userinfo_url = "https://api.weixin.qq.com/cgi-bin/user/info";

    //全局获取token
    protected final static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

    //创建菜单
    protected final static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";

    //查询菜单
    protected final static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=";

    //删除菜单
    protected final static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=";

    //发送模板消息
    protected final static String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //新增临时素材
    protected final static String UPLOAD_MEDIA_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=";

    //新增永久素材
    protected final static String ADD_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=";

    //获取素材列表
    protected final static String BATCHGET_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material?access_token=";

    protected static final long TOKEN_TIME_OUT = 60 * 60 * 1000L;

    @Value("${weixin.appID}")
    protected String appid;

    @Value("${weixin.appsecret}")
    protected String secret;

    protected String domainName = "http://www.taohuijie.work";

    @Value("${weixin.templateId}")
    protected String templateId;

    @Autowired
    protected IRedisJsonStringService redisJsonStringService;

}
