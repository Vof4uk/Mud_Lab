package ua.mykytenko.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.web.testing.TestingController;
import ua.mykytenko.web.testing.mud.MudController;
import ua.mykytenko.web.testing.powder.PowdersController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static ua.mykytenko.util.ServletUtil.getId;

/**
 * Created by Микитенко on 19.10.2016.
 */
public class TestingServlet extends HttpServlet {

    private ClassPathXmlApplicationContext springContext;

    private List<TestingController> controllers = new ArrayList<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        controllers.add(springContext.getBean(PowdersController.class));
        controllers.add(springContext.getBean(MudController.class));
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if("show".equals(action)){
            Sample s = (Sample) req.getAttribute("sample");
            List<Map<String, String>> parameterMapsList = controllers.stream().map(controller -> controller.get(getId(req))
                    .getParametersMap()).collect(Collectors.toList());
            req.setAttribute("parameterMapsList", parameterMapsList);
            req.getRequestDispatcher("sampleWithTests.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
