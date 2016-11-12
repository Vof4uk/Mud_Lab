package ua.mykytenko.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Микитенко on 26.10.2016.
 */
public class ServletUtil {

    private static ClassPathXmlApplicationContext springContext;

    public static int parseInt(String str){
        if(str == null || !str.matches("\\d+")) return -1;
        return Integer.parseInt(str);
    }

    public static ClassPathXmlApplicationContext getSpringContext(){
        if(springContext == null)
        {
            springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        }
        return springContext;
    }
}
