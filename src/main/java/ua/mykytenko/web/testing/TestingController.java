package ua.mykytenko.web.testing;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
public interface TestingController<T extends AbstractTesting> {
    T get(int id);

    void delete(int id);

    List<T> getAll();

    List<T> getAllBySampleId(int sampleId);

    T save(T testing);
}
