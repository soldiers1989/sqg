package com.soft.wechat.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.domain.UserSession;
import com.soft.wechat.service.IWechatService;

/**
 * 微信二维码控制器
 * 
 * @author junxian.zhao
 *
 */
@RestController
@RequestMapping(value = "/laserDirect/wx",produces = MediaType.APPLICATION_JSON_VALUE)
public class WechatCon extends BaseController {

    Logger logger = LoggerFactory.getLogger(WechatCon.class);

    @Autowired
    IWechatService wechatService;

    /**
     * 微信生成生成分享二维码
     * 
     * @param request
     * @param code
     * @return
     */
    @RequestMapping(value = "/generateWxQrCode/{code}")
    public @ResponseBody ResultResponse generateWxQrCode(HttpServletRequest request, @PathVariable("code") String code) {

        UserSession userSession = getUserSession(request);
        if (userSession != null) {
            code = userSession.getId().toString();
        }
        String url = wechatService.createQrcode(code);
        return new ResultResponse(url);
    }

    /**
     * 微信生成生成分享二维码
     * 
     * @param request
     * @param code
     * @return
     */
    @RequestMapping(value = "/generateWxLimitQrCode/{code}")
    public @ResponseBody ResultResponse generateWxLimitQrCode(HttpServletRequest request, @PathVariable("code") String code) {

        UserSession userSession = getUserSession(request);
        if (userSession != null) {
            code = userSession.getId().toString();
        }
        String url = wechatService.createLimitQrcode(code);
        return new ResultResponse(url);
    }

    /**
     * 创建菜单
     * 
     * @param request
     * @param menuJson
     * @return
     */
    @RequestMapping(value = "/menu/create")
    public @ResponseBody ResultResponse createMenu(HttpServletRequest request, @RequestBody JSONObject json) {

        String menuJson = json.toJSONString();
        String result = wechatService.createMenu(menuJson);
        return new ResultResponse(result);
    }

    /**
     * 查询菜单
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu/get")
    public @ResponseBody ResultResponse getMenu(HttpServletRequest request) {

        String result = wechatService.getMenu();
        return new ResultResponse(result);
    }

    /**
     * 删除菜单
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/menu/delete")
    public @ResponseBody ResultResponse deleteMenu(HttpServletRequest request) {

        String result = wechatService.deleteMenu();
        return new ResultResponse(result);
    }

    /**
     * 获取素材列表
     * 
     * @param request
     * @param type 图片（image）、视频（video）、语音 （voice）、图文（news）
     * @param offset 从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
     * @param count 返回素材的数量，取值在1到20之间
     * @return
     */
    @RequestMapping(value = "/batchgetMaterial/{type}/{offset}/{count}")
    public @ResponseBody ResultResponse deleteMenu(HttpServletRequest request, @PathVariable("type") String type,
                    @PathVariable("offset") String offset, @PathVariable("count") String count) {

        String result = wechatService.batchgetMaterial(type, offset, count);
        return new ResultResponse(result);
    }

}
