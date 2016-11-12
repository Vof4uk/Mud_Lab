package ua.mykytenko.web.controllers.testing;

import ua.mykytenko.entities.tests.AbstractTesting;
import ua.mykytenko.to.TestingTo;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
public interface TestingController<T extends AbstractTesting> {

    TestingTo get(int id, int userId);
    void delete(int id, int userId);
    List<TestingTo> getAll(int userId);
    List<TestingTo> getAllBySampleId(int sampleId, int userId);
    TestingTo save(T testing, int userId);
}
