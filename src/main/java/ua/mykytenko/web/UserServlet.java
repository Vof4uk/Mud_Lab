package ua.mykytenko.web;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class UserServlet extends HttpServlet {

    private final ClassPathXmlApplicationContext appContext;

    public UserServlet(ClassPathXmlApplicationContext appContext) {
        this.appContext = appContext;
    }


    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
