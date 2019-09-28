package com.funtl.my.shop.dao.impl;

import com.funtl.my.shop.dao.UserDao;
import com.funtl.my.shop.entity.User;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository(value = "userDao")
public class UserDaoImpl implements UserDao {
    private static final Logger logger=LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUser(String email, String password) {
        logger.debug("调用getUser（）,参数位email:{},Password:{}",email, password);
        User user=null;
        if ("528854302@qq.com".equals(email)){
            if ("admin".equals(password)){
                user=new User();
                user.setEmail("528854302@qq.com");
                user.setUsername("Lusifer");
                logger.info("成功获取\"{}\"的用户信息",user.getUsername());
                return user;
            }
        }else {
            logger.warn("获取\"{}\"的用户信息失败",email);
        }
        return null;
    }
}
