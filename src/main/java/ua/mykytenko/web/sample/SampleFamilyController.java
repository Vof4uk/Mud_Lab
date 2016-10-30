package ua.mykytenko.web.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.service.SampleFamilyService;

import java.util.List;

/**
 * Created by Микитенко on 26.10.2016.
 */
@Controller
public class SampleFamilyController {

    @Autowired
    SampleFamilyService service;


    public List<SampleFamily> getAll(){
        return service.getAll();
    }

    public SampleFamily createFamily(SampleFamily family){
        return service.create(family);
    }

    public SampleFamily get(int id){
        return service.get(id);
    }
}
