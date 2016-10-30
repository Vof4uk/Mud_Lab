package ua.mykytenko.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.mykytenko.entities.samples.Sample;

import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.web.sample.SampleFamilyController;
import ua.mykytenko.web.sample.SampleController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

import static ua.mykytenko.entities.samples.Description.*;
import static ua.mykytenko.util.ServletUtil.*;
/**
 * Created by Микитенко on 20.10.2016.
 */
@Configuration
public class SampleServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(SampleServlet.class);

    private ClassPathXmlApplicationContext springContext;
    private SampleFamilyController familyController;
    private SampleController controller;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        familyController = springContext.getBean(SampleFamilyController.class);
        controller = springContext.getBean(SampleController.class);

    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action ==  null){
            req.setAttribute("sampleList", controller.getAll());
            req.getRequestDispatcher("/samples.jsp").forward(req, resp);
        }
        else if("delete".equals(action)){
            controller.delete(getId(req));
            resp.sendRedirect("samples");
        }
        else if("update".equals(action)){
            Sample sample = controller.get(getId(req));
            req.setAttribute("sample", sample);
            req.setAttribute("families", familyController.getAll());
            req.getRequestDispatcher("editSample.jsp").forward(req, resp);
        }
        else if("createSample".equals(action)){
            Sample sample = new Sample();
            sample.setArrived(LocalDate.now());
            req.setAttribute("sample", sample);
            req.setAttribute("families", familyController.getAll());
            req.getRequestDispatcher("editSample.jsp").forward(req, resp);
        }
        else if("show".equals(action)){
            if(req.getParameter("id").isEmpty()) return;
            int id = getId(req);
            req.setAttribute("sample", controller.get(id));
            req.getRequestDispatcher("tests?action=show&id=" + id).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");


        if("saveSample".equals(action))
        {
            final Sample sample = new Sample(
                    "polymers", req.getParameter("name"),
                    Float.valueOf(req.getParameter("weight")),
                    LocalDate.parse(req.getParameter("arrived")), Applications.parse(req.getParameter("applications")),
                    Components.parse(req.getParameter("composition")), new Vendor(req.getParameter("vendor")),
                    new Manufacturer(req.getParameter("manufacturer")), "");
            if (req.getParameter("id").isEmpty()) {
                controller.save(sample);
            } else {
                sample.setId(Integer.valueOf(req.getParameter("id")));
                controller.update(sample);
            }
                resp.sendRedirect("samples.jsp");
        }
        else if("saveFamily".equals(action))
        {
            int initId = Integer.valueOf(req.getParameter("initialId"));
            String name = req.getParameter("name");
            SampleFamily family = new SampleFamily(name, initId);

            if (req.getParameter("id").isEmpty()) {
                familyController.createFamily(family);
            } else {
                family.setId(Integer.valueOf(req.getParameter("id")));
                familyController.createFamily(family);
            }
            resp.sendRedirect("families");
        }

    }

}

