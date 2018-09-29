package com.soft.tbk.core.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.soft.tbk.domain.UserSession;
import com.soft.wechat.service.IWechatService;

@Component
public class MainHandlerInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(MainHandlerInterceptor.class);

    @Autowired
    private IWechatService wechatService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        UserSession userSession = (UserSession) request.getSession().getAttribute("userSession");
        String requestPath = getRequestservice(request);
        String contentPath = request.getServletContext().getContextPath();
        String url = request.getServletPath();
        if (userSession == null) {
            if (url.startsWith("/web/account")) {
                // 暂时就个人中心
                wechatService.requestAuth(request, response, contentPath, url);
            } else {
                // TODO
                //                logger.info("没有登录");
                //                return false;
            }
        }
        request.setAttribute("sysContextPath", requestPath + contentPath);
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
                    throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    protected String getRequestservice(HttpServletRequest request) {

        int port = request.getServerPort();
        String str = request.getScheme() + "://" + request.getServerName();
        if (port != 80) {
            str += ":" + port;
        }
        return str;
    }
}
