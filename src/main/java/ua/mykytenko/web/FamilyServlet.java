package ua.mykytenko.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.web.sample.SampleFamilyController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Микитенко on 26.10.2016.
 */
public class FamilyServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(FamilyServlet.class);

    private SampleFamilyController familyController;

    private ClassPathXmlApplicationContext springContext;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null || action.isEmpty()){
            req.setAttribute("families", familyController.getAll());
            req.getRequestDispatcher("editFamily.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        familyController = springContext.getBean(SampleFamilyController.class);
    }
}
