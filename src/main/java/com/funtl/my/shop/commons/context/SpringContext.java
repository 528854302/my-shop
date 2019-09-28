package com.funtl.my.shop.commons.context;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringContext implements ApplicationContextAware,DisposableBean{
    private static ApplicationContext applicationContext;

    private static final Logger logger= LoggerFactory.getLogger(SpringContext.class);

    public static <T> T getBean(String beanId){
        return (T) applicationContext.getBean(beanId);
    }
    public static <T> T getBean(Class<T> Clazz){
        assertContextInjected();
        return applicationContext.getBean(Clazz);
    }



    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext=applicationContext;
    }

    public void destroy() throws Exception {
        logger.debug(" destroy()——————清除，applicationContext=null");
        applicationContext=null;
    }
    private static void assertContextInjected(){
        Validate.validState(applicationContext != null,"spring-context.xml中未配置SpringContext对象");

    }
}




