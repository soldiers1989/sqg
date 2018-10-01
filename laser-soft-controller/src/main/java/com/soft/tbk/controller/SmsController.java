package com.soft.tbk.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.util.StringUtil;
import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.core.cache.IRedisClientKValue;
import com.soft.tbk.service.channel.aliyunsms.AliyunSmsService;
import com.soft.tbk.utils.RandomUtil;

@Controller
@RequestMapping("/laserDirect/sms")
public class SmsController extends BaseController {

    @Autowired
    IRedisClientKValue<String> redisClientKValue;

    @Autowired
    AliyunSmsService aliyunSmsService;

    /**
     * 发送短信验证码
     * 
     * @param request
     * @param phoneNo
     * @return
     */
    @RequestMapping("/getCode")
    @ResponseBody
    public ResultResponse getCode(HttpServletRequest request, String phoneNo) {

        String key = "code-" + phoneNo;
        String code = redisClientKValue.get(appkey, key, String.class);
        if (StringUtil.isEmpty(code)) {
            code = RandomUtil.getRandom(6);
            redisClientKValue.set(appkey, key, code, 5 * 60 * 1000);// 5分钟失效
        }
        aliyunSmsService.sendSms(phoneNo, code);
        return new ResultResponse();
    }

}
