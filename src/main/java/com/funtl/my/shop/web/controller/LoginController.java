package com.funtl.my.shop.web.controller;


import com.funtl.my.shop.commons.context.SpringContext;
import com.funtl.my.shop.commons.utils.CookieUtils;
import com.funtl.my.shop.entity.User;
import com.funtl.my.shop.service.UserService;
import com.funtl.my.shop.service.impl.UserServiceImpl;
import org.apache.commons.logging.impl.ServletContextCleaner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    /*
    跳转登录页面
     */
    @RequestMapping(value = {"","login"},method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    /*
    登录逻辑
     */
    @RequestMapping(value = "login",method = RequestMethod.POST)
    public String login(@RequestParam(required = true)String email,@RequestParam(required = true)  String password,HttpServletRequest request){
        User user=userService.login(email,password);
        if (user==null){
            return login();
        }
        else {
            request.getSession().setAttribute("user",user);
            return "redirect:/main";
        }
    }

}




















    /*private UserService userService=SpringContext.getBean(UserServiceImpl.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("###############@@@@@@@@@@@@@@@@@@");
        String userInfo = CookieUtils.getCookieValue(req,"userInfo",true);
        String[] str = userInfo.split(":");
        System.out.println(str[0]+","+str[1] );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email=req.getParameter("email");
        String password=req.getParameter("password");
        boolean isRemember=req.getParameter("isRemember")==null?false:true;
        User admin=userService.login(email,password);

         if (admin!=null){
           if (isRemember){
               CookieUtils.setCookie(req,resp,"userInfo",String.format("%s:%s",email,password),5*60*60);
           }
            resp.sendRedirect("/main.jsp");
        }else {
            req.setAttribute("message","email or password wrong");
            req.getRequestDispatcher("index.jsp").forward(req,resp);

        }
    }
}*/
