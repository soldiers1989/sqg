package com.soft.tbk.admin;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.soft.tbk.base.BaseController;
import com.soft.tbk.base.ResultResponse;
import com.soft.tbk.domain.UserSession;

@Controller
@RequestMapping("/admin")
public class IndexController extends BaseController{
    
    @RequestMapping("/index")
    public String page(ModelMap model) {

        return "/admin/index";
    }
    @RequestMapping("/login")
    public String login(ModelMap model) {

        return "/admin/login";
    }
    @ResponseBody
    @RequestMapping("/login.json")
    public ResultResponse doLogin(ModelMap model, HttpServletRequest request) {
        String code = request.getParameter("code");
        String pwd = request.getParameter("pwd");
        if ("admin".equals(code) && "admin888".equals(pwd)) {
            UserSession userSession = new UserSession();
            userSession.setUserNickname("admin");
            request.getSession().setAttribute("userSession", userSession);
            return new ResultResponse(true);
        }else {
            return new ResultResponse("error","用户名密码错误");
        }
    }
}
