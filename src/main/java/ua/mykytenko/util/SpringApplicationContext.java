package ua.mykytenko.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by Микитенко on 19.11.2016.
 */
@Configuration
public class SpringApplicationContext implements ApplicationContextAware{

    private static ApplicationContext CONEXT;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        CONEXT = applicationContext;
    }

    public static Object getBean(String name){
        return CONEXT.getBean(name);
    }

    public static Object getBean(Class c){
        return CONEXT.getBean(c);
    }


}
