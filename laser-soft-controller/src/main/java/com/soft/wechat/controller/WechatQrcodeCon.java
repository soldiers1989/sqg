package com.soft.wechat.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.soft.tbk.base.ResultResponse;
import com.soft.wechat.dto.WxQrcodeActionDTO;
import com.soft.wechat.model.WxQrcode;
import com.soft.wechat.service.IWechatService;
import com.soft.wechat.service.SuperWechatService;
import com.soft.wechat.util.WebUtils;

/**
 * 微信二维码控制器
 * 
 * @author junxian.zhao
 *
 */
@RestController
@RequestMapping(value = "/web/wx/qrcode",produces = MediaType.APPLICATION_JSON_VALUE)
public class WechatQrcodeCon extends SuperWechatService {

    Logger logger = LoggerFactory.getLogger(WechatQrcodeCon.class);

    @Autowired
    IWechatService wechatService;

    /**
     * 微信生成生成分享二维码
     * 
     * @param request
     * @param code
     * @param type 如[event_qrcode]，mnsconfig中的name
     * @return
     */
    @RequestMapping(value = "/generateWxQrCode/{code}")
    public @ResponseBody ResultResponse generateWxQrCode(HttpServletRequest request, @PathVariable("code") String code) {

        WxQrcode wxQrcode = createQrcode(QR_STR_SCENE, THIRTY_DAY, null, code);
        if (wxQrcode == null) {
            return new ResultResponse("Fail", "fail to create qrcode");
        }
        String url = QRCODE_SHOW + wxQrcode.getTicket();
        logger.info("分享二维码url:" + url);
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
        qrcodeActionDTO.setExpire_seconds(expireSeconds);
        qrcodeActionDTO.setAction_name(actionName);
        qrcodeActionDTO.setAction_info(actionInfo);

        String url = QRCODE_CREATE_URL + wechatService.getAccessTokenCache();
        try {
            String json = qrcodeActionDTO.toString();
            return WebUtils.postForObject(url, json, WxQrcode.class, 0, 0);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }

}
