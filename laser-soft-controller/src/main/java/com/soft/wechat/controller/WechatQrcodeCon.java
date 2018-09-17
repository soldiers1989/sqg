package com.soft.wechat.controller;

import static org.apache.commons.lang3.StringUtils.join;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.soft.tbk.base.ResultResponse;
import com.soft.wechat.dto.WxQrcodeActionDTO;
import com.soft.wechat.model.WxQrcode;
import com.soft.wechat.util.WebUtils;

/**
 * 微信二维码控制器
 * 
 * @author junxian.zhao
 *
 */
@RestController
@RequestMapping(value = "/web/wx/qrcode",produces = MediaType.APPLICATION_JSON_VALUE)
public class WechatQrcodeCon {

    Logger logger = LoggerFactory.getLogger(WechatQrcodeCon.class);

    /**
     * 永久的字符串参数值
     */
    private final static String QR_LIMIT_STR_SCENE = "QR_LIMIT_STR_SCENE";

    /**
     * 永久的整型参数值
     */
    private final static String QR_LIMIT_SCENE = "QR_LIMIT_SCENE";

    /**
     * 临时的字符串参数值
     */
    private final static String QR_STR_SCENE = "QR_STR_SCENE";

    private final static String QRCODE_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=";

    private final static String QRCODE_SHOW = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

    private static final int THIRTY_DAY = 2_592_000;

    private static final int SCENE_STR_MAX_LENGTH = 64;

    /**
     * 微信生成生成分享二维码
     * 
     * @param request
     * @param code
     * @param type 如[event_qrcode]，mnsconfig中的name
     * @return
     */
    @RequestMapping(value = "generateWxQrCode/{tenantCode}/{code}/{type}")
    public @ResponseBody ResultResponse generateWxQrCode(HttpServletRequest request, @PathVariable("code") String code,
                    @PathVariable("type") String type) {

        WxQrcode wxQrcode = createQrcode(QR_STR_SCENE, THIRTY_DAY, null, getSceneStr(code, type));
        if (wxQrcode == null) {
            return new ResultResponse("Fail", "fail to create qrcode");
        }
        String url = QRCODE_SHOW + wxQrcode.getTicket();
        return new ResultResponse(url);
    }

    /**
     * 创建二维码
     *
     * @param actionName
     *            二维码类型
     * @param expireSeconds
     *            二维码有效时间
     * @param sceneId
     *            场景值数字ID 临时二维码时为32位非0整型
     * @param sceneStr
     *            场景值字符串ID 长度限制为1到64
     * @return
     */
    private WxQrcode createQrcode(String actionName, Integer expireSeconds, Integer sceneId, String sceneStr) {

        if (StringUtils.isBlank(actionName)) {
            return null;
        }

        // 场景数据
        String actionInfo = null;
        if (sceneId != null) {
            actionInfo = String.format("{\"scene\":{\"scene_id\":%d}}", sceneId);
        }
        if (StringUtils.isNotBlank(sceneStr)) {
            if (sceneStr.length() > SCENE_STR_MAX_LENGTH) {
                throw new IllegalArgumentException("sceneStr length more than 64");
            }
            actionInfo = String.format("{\"scene\":{\"scene_str\":\"%s\"}}", sceneStr);
        }

        WxQrcodeActionDTO qrcodeActionDTO = new WxQrcodeActionDTO();
        qrcodeActionDTO.setExpireSeconds(expireSeconds);
        qrcodeActionDTO.setActionName(actionName);
        qrcodeActionDTO.setActionInfo(actionInfo);

        String url = QRCODE_CREATE_URL + UUID.randomUUID().toString();
        try {
            String json = JSONArray.toJSONString(qrcodeActionDTO);
            return WebUtils.postForObject(url, json, WxQrcode.class, 0, 0);
        } catch (IOException e) {}
        return null;
    }

    /**
     * 格式：业务code#mnsconfigName
     * 
     * @param code 业务方代码
     * @param mnsConfigName 短信配置名称
     * @return sceneStr
     */
    private String getSceneStr(String code, String mnsConfigName) {

        return join(code, "#", mnsConfigName);
    }

}
