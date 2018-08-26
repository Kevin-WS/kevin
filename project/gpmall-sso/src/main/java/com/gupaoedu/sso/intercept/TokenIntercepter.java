package com.gupaoedu.sso.intercept;

import com.gupaoedu.sso.constants.GpmallWebConstant;
import com.gupaoedu.sso.controller.Anoymous;
import com.gupaoedu.sso.controller.BaseController;
import com.gupaoedu.sso.utils.CookieUtils;
import com.gupaoedu.user.IUserCoreService;
import com.gupaoedu.user.dto.CheckAuthRequest;
import com.gupaoedu.user.dto.CheckAuthResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @description: Tocken拦截器
 * @author: Wu Shuai
 * @create: 2018-08-26 12:13
 **/
public class TokenIntercepter extends HandlerInterceptorAdapter {

    private final String ACCESS_TOKEN = "access_token";

    @Autowired
    IUserCoreService userCoreService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Object bean = handlerMethod.getBean();
        if (isAnoymous(handlerMethod)) {
            return true;
        }
        if (!(bean instanceof BaseController)) {
            throw new RuntimeException("must extend basecontroller");
        }
        String token = CookieUtils.getCookieValue(request, ACCESS_TOKEN);
        boolean isAjax = CookieUtils.isAjax(request);
        if (StringUtils.isEmpty(token)) {
            if (isAjax) {
                response.setContentType("text/html;charset=UTF-8");
                response.getWriter().write("{\"code\":\"-1\",\"msg\":\"error\"}");
                return false;
            }
            response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
            return false;
        }
        CheckAuthRequest checkAuthRequest = new CheckAuthRequest();
        checkAuthRequest.setToken(token);
        CheckAuthResponse checkAuthResponse = userCoreService.validToken(checkAuthRequest);
        if ("000000".equals(checkAuthResponse.getCode())) {
            BaseController baseController = (BaseController) bean;
            baseController.setUid(checkAuthResponse.getUid());
            return super.preHandle(request, response, handler);
        }
        if (isAjax) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("{\"code\":\"" + checkAuthResponse.getCode() + "\"" +
                    ",\"msg\":\"" + checkAuthResponse.getMsg() + "\"}");
            return false;
        }
        response.sendRedirect(GpmallWebConstant.GPMALL_SSO_ACCESS_URL);
        return false;
    }

    /**
     * 判断是否匿名登录
     *
     * @param handlerMethod
     * @return
     */
    private boolean isAnoymous(HandlerMethod handlerMethod) {
        Object bean = handlerMethod.getBean();
        Class clazz = bean.getClass();
        if (clazz.isAnnotationPresent(Anoymous.class)) {
            return true;
        }

        Method method = handlerMethod.getMethod();
        return method.isAnnotationPresent(Anoymous.class);
    }
}
