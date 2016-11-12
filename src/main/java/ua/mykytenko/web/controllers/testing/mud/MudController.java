package ua.mykytenko.web.controllers.testing.mud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.entities.user.UserRole;
import ua.mykytenko.service.TestingService;
import ua.mykytenko.to.TestingTo;
import ua.mykytenko.web.controllers.AbstractController;
import ua.mykytenko.web.controllers.testing.TestingController;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Controller
public class MudController extends AbstractController implements TestingController<BasicMudTesting>{

    @Autowired
    private TestingService<BasicMudTesting> service;

    public TestingTo get(int id, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.get(id);
    }

    public void delete(int id, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF);
        service.delete(id);
    }

    public List<TestingTo> getAll(int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.getAll();
    }

    public TestingTo save(BasicMudTesting testing, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST);
        return service.save(testing);
    }

    @Override
    public List<TestingTo> getAllBySampleId(int sampleId, int userId) {
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.getAllBySampleId(sampleId);
    }
}
