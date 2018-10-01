package com.soft.tbk.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.soft.tbk.domain.UserSession;
import com.soft.tbk.utils.MapUtil;
import com.soft.tbk.utils.StringUtils;

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
    
    
    protected void polishQueryMap(Map<String, Object> params) {
        if (MapUtil.isNotEmpty(params)) {
            
            if (params.get("sort") != null && StringUtils.isNotEmpty(String.valueOf(params.get("sort")))) {
                String sort = String.valueOf(params.get("sort"));
                sort = StringUtils.humpToUnderline(sort);
                String sortBy = String.valueOf(params.get("sortBy"));
                params.put("order", true);
                params.put("orderStr", sort + " " + sortBy);
            }else {
                params.put("order", true);
            }
            if (params.containsKey("pageNum")) {
                params.put("pageNum", Integer.valueOf((String)params.get("pageNum")));
            }
            if (params.containsKey("pageSize")) {
                params.put("pageSize", Integer.valueOf((String)params.get("pageSize")));

            }
        }
    }
}
