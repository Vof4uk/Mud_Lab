package ua.mykytenko.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Микитенко on 26.10.2016.
 */
public class ServletUtil {

    private static ClassPathXmlApplicationContext springContext;

    public static int getId(HttpServletRequest req){
        String idStr = req.getParameter("id");
        if(idStr == null |! idStr.matches("\\d+")) return -1;
        return Integer.parseInt(idStr);
    }

    public static ClassPathXmlApplicationContext getSpringContext(){
        if(springContext == null)
        {
            springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        }
        return springContext;
    }
}
