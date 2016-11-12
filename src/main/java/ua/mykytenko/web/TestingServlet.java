package ua.mykytenko.web;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ua.mykytenko.to.TestingTo;
import ua.mykytenko.web.controllers.testing.TestingController;
import ua.mykytenko.web.controllers.testing.mud.MudController;
import ua.mykytenko.web.controllers.testing.powder.PowdersController;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static ua.mykytenko.util.ServletUtil.parseInt;

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
        final Integer userId = req.getSession().getAttribute("userId") == null ? 0 : (Integer)req.getSession().getAttribute("userId");

        if("show".equals(action)){
            List<TestingTo> parameterMapsList = controllers.stream()
                    .map(controller -> controller.getAllBySampleId(parseInt(req.getParameter("id"))
                            , userId))
                    .collect(ArrayList<TestingTo>::new, ArrayList::addAll, (l, p) -> {});
            req.setAttribute("toList", parameterMapsList);
            req.getRequestDispatcher("sampleWithTests.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        final int userId = parseInt((String) req.getSession().getAttribute("userId"));
    }
}
