package ua.mykytenko.web.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.service.SamplesService;

import java.util.List;

/**
 * Created by Микитенко on 20.10.2016.
 */
@Controller
public class SampleController {

    @Autowired
    SamplesService service;


    public void delete(int id){
        service.delete(id);
    }

    public void update(Sample sample){
        if(sample.getId() == null) return;
        service.update(sample);
    }

    public void save(Sample sample){
        sample.setId(null);
        service.saveNew(sample);
    }

    public Sample get(int id){
        return service.get(id);
    }

    public List<Sample> getAll(){
        return service.getAll();
    }

}
