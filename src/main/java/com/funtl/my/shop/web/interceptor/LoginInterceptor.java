package com.funtl.my.shop.web.interceptor;

import com.funtl.my.shop.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if (user == null){
            httpServletResponse.sendRedirect("/login");
        }

        return true;//放行
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("modelAndView.getViewName(),:@@@@@"+modelAndView.getViewName());
        if (modelAndView.getViewName().endsWith("login")){
            User user = (User) httpServletRequest.getSession().getAttribute("user");
            if (user!=null){
                httpServletResponse.sendRedirect("/main");
            }
        }
    }


    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
