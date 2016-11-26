package ua.mykytenko.web.controllers.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.entities.user.UserRole;
import ua.mykytenko.service.SampleFamilyService;
import ua.mykytenko.web.controllers.AbstractController;

import java.util.List;

/**
 * Created by Микитенко on 26.10.2016.
 */
@Controller
public class SampleFamilyController extends AbstractController{

    @Autowired
    private SampleFamilyService service;


    public List<SampleFamily> getAll(int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.getAll();
    }

    public SampleFamily create(SampleFamily family, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF);
        return service.saveNew(family);
    }

    public SampleFamily get(int id, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.get(id);
    }

    public void delete(int id, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF);
        service.delete(id);
    }
}
