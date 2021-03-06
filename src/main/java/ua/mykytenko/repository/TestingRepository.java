package ua.mykytenko.repository;

import ua.mykytenko.entities.tests.AbstractTesting;

import java.util.List;

/**
 * Created by Микитенко on 28.10.2016.
 */
public interface TestingRepository<T extends AbstractTesting> {
    T get(int id);
    boolean delete(int id);
    List<T> getAll();
    T save(T test);
    List<T> getAllBySampleId(int sampleId);
}
