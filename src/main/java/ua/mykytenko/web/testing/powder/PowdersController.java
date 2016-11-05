package ua.mykytenko.web.testing.powder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.web.testing.TestingController;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.service.TestingService;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Controller
public class PowdersController implements TestingController<BasicPowderTesting> {

    @Autowired
    private TestingService<BasicPowderTesting> service;

    @Override
    public BasicPowderTesting get(int id) {
        return service.get(id);
    }

    @Override
    public void delete(int id) {
        service.delete(id);
    }

    @Override
    public List<BasicPowderTesting> getAll() {
        return service.getAll();
    }

    @Override
    public BasicPowderTesting save(BasicPowderTesting testing) {
        return service.save(testing);
    }

    @Override
    public List<BasicPowderTesting> getAllBySampleId(int sampleId) {
        return service.getAllBySampleId(sampleId);
    }
}
