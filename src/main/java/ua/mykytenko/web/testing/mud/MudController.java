package ua.mykytenko.web.testing.mud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.service.TestingService;
import ua.mykytenko.web.testing.TestingController;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Controller
public class MudController implements TestingController<BasicMudTesting>{

    @Autowired
private TestingService<BasicMudTesting> service;

    public BasicMudTesting get(int id){
        return service.get(id);
    }

    public void delete(int id){
        service.delete(id);
    }

    public List<BasicMudTesting> getAll(){
        return service.getAll();
    }

    public BasicMudTesting save(BasicMudTesting testing){
        return service.save(testing);
    }
}
