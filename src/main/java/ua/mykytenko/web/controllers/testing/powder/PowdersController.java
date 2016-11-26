package ua.mykytenko.web.controllers.testing.powder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
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
public class PowdersController extends AbstractController implements TestingController<BasicPowderTesting> {

    @Autowired
    private TestingService<BasicPowderTesting> service;

    @Override
    public TestingTo get(int id, int userId) {
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.get(id);
    }

    @Override
    public void delete(int id, int userId) {
        checkUserForAccess(userId, UserRole.LAB_CHIEF);
        service.delete(id);
    }

    @Override
    public List<TestingTo> getAll(int userId) {
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.getAll();
    }

    @Override
    public TestingTo save(BasicPowderTesting testing, int userId) {
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST);
        return service.saveNew(testing);
    }

    @Override
    public List<TestingTo> getAllBySampleId(int sampleId, int userId) {
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.getAllBySampleId(sampleId);
    }
}
