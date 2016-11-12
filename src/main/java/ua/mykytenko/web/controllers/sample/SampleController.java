package ua.mykytenko.web.controllers.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.entities.user.UserRole;
import ua.mykytenko.service.SamplesService;
import ua.mykytenko.web.controllers.AbstractController;

import java.util.List;

/**
 * Created by Микитенко on 20.10.2016.
 */
@Controller
public class SampleController extends AbstractController{

    @Autowired
    SamplesService service;


    public void delete(int sampleId, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST);
        service.delete(sampleId);
    }

    public void update(Sample sample, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST );
        if(sample.getId() == null) return;
        service.update(sample);
    }

    public void save(Sample sample,  int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST );
        sample.setId(null);
        service.saveNew(sample);
    }

    public Sample get(int id, int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.get(id);
    }

    public List<Sample> getAll(int userId){
        checkUserForAccess(userId, UserRole.LAB_CHIEF, UserRole.LAB_ASSIST, UserRole.BOSS );
        return service.getAll();
    }
}
