package com.soft.wechat.controller;

import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.soft.wechat.model.Environment;
import com.soft.wechat.service.BusinessService;
import com.soft.wechat.service.WechatService;
import com.soft.wechat.ui.StartUI;
import com.soft.wechat.util.CheckUtil;

@Controller
@RequestMapping("/laserDirect")
public class WeChatController {

    public static final String SYS_CODE = "WeChatController.";

    private static Logger logger = LoggerFactory.getLogger(WeChatController.class);

    @Autowired
    WechatService wechatService;

    @Autowired
    BusinessService businessCon;

    private static final ExecutorService pool = Executors.newFixedThreadPool(100);

    /**
     * 验证微信服务器
     * 
     * @param response
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     */
    @RequestMapping(value = "/wechat/{tenantCode}",method = RequestMethod.GET)
    public void wechatService(PrintWriter out, @PathVariable("tenantCode") String tenantCode,
                    @RequestParam(value = "signature",required = false) String signature, @RequestParam String timestamp,
                    @RequestParam String nonce, @RequestParam String echostr) {

        logger.info("signature：" + signature + "\ntimestamp：" + timestamp + "\nnonce：" + nonce + "\nechostr：" + echostr);
        if (CheckUtil.checkSignature(signature, timestamp, nonce, tenantCode)) {
            logger.info(echostr);
            out.print(echostr);
            out.flush();
        } else {
            logger.info(SYS_CODE + "checkSignature false");
        }
    }

    @ResponseBody
    @RequestMapping(value = "/wechat/{tenantCode}",method = RequestMethod.POST)
    public void wechatServicePost(HttpServletRequest request, HttpServletResponse response,
                    @PathVariable("tenantCode") String tenantCode) {

        try {
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            String responseMessage = wechatService.processRequest(request, tenantCode);
            logger.info(SYS_CODE + "returnMessage:" + responseMessage);
            out.print(responseMessage);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void syncStart(final StartUI startUI) {

        if (pool != null) {
            pool.execute(new Runnable() {

                @Override
                public void run() {

                    startUI.start();
                }
            });
        }
    }

    /**
     * 机器人
     * 
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wechatLogin/robot/{tenantCode}",method = RequestMethod.GET)
    public String tulingLogin(HttpServletRequest request, HttpServletResponse response, @PathVariable("tenantCode") String tenantCode) {

        System.setProperty("jsse.enableSNIExtension", "false");
        Environment environment = Environment.of("classpath:config.properties");
        StartUI startUI = new StartUI(environment);
        startUI.setTenantCode(tenantCode);
        startUI.setBusinessCon(businessCon);
        startUI.setIamgeImg(request.getSession().getServletContext().getRealPath(""));
        syncStart(startUI);
        String path = "";
        while (true) {
            path = startUI.getPath();
            if (StringUtils.isEmpty(path)) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
                continue;
            }
            logger.info("二维码url==========>>> " + path);
            break;
        }
        request.setAttribute("path", "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + path);
        return "index";
    }
}
