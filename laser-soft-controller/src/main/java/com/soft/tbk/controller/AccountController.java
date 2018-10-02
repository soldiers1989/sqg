package com.soft.tbk.controller;

import java.math.BigDecimal;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.constants.TbkConstants;
import com.soft.tbk.domain.UserSession;
import com.soft.tbk.model.TbkAccount;
import com.soft.tbk.model.TbkQrcode;
import com.soft.tbk.service.TbkAccountService;
import com.soft.tbk.service.TbkQrcodeService;
import com.soft.wechat.service.IWechatService;

@Controller
@RequestMapping("/web/account")
public class AccountController extends BaseController {

    @Autowired
    private TbkQrcodeService tbkQrcodeService;

    @Autowired
    private IWechatService wechatService;

    @Autowired
    private TbkAccountService tbkAccountService;

    @RequestMapping("/index")
    public String index(ModelMap model, HttpServletRequest request) {

        UserSession user = getUserSession(request);
        model.put("user", user);

        TbkAccount account = tbkAccountService.getTbkAccountByUserId(user.getId());

        BigDecimal abAmount = BigDecimal.ZERO;
        if (account != null) {
            abAmount = account.getAccountAmountA();
        }
        model.put("amount", abAmount);

        return "/h5/account/index";
    }

    @RequestMapping("/wxQrCode")
    public String wxQrCode(ModelMap model, HttpServletRequest request) {

        UserSession userSession = getUserSession(request);
        Integer userId = userSession.getId();
        String url = tbkQrcodeService.getTbkQrcode(userSession.getId(), TbkConstants.QR_TYPE_0);
        if (StringUtils.isBlank(url)) {
            url = wechatService.createQrcode(userId.toString());
            TbkQrcode tbkQrcode = new TbkQrcode();
            tbkQrcode.setQrType(TbkConstants.QR_TYPE_0);
            tbkQrcode.setQrUrl(url);
            tbkQrcode.setUserId(userId);
            tbkQrcodeService.saveTbkQrcode(tbkQrcode);
        }
        model.put("url", url);
        return "/h5/account/wxQrCode";
    }

}
