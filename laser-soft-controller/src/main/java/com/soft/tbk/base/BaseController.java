package com.soft.tbk.base;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soft.tbk.domain.UserSession;

public class BaseController {

    protected final static String appkey = "tbk";

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected UserSession getUserSession(HttpServletRequest request) {

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");

        if (userSession == null) {
            userSession = new UserSession();
            userSession.setUserNickname("体验账号");
            userSession.setUserImgurl("http://gw.alicdn.com/bao/uploaded/i4/TB1rQ6dMVXXXXbwXXXXXXXXXXXX_!!0-item_pic.jpg");
            userSession.setUserLevel("5");
            userSession.setId(5);
        }
        return userSession;
    }

    protected void setUserSession(HttpServletRequest request, UserSession userSession) {

        request.getSession().setAttribute("userSession", userSession);

    }
}
