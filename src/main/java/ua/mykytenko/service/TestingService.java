package ua.mykytenko.service;

import ua.mykytenko.entities.tests.AbstractTesting;
import ua.mykytenko.to.TestingTo;

import java.util.List;

/**
 * Created by Микитенко on 20.10.2016.
 */
public interface TestingService<T extends AbstractTesting> {
    TestingTo get(int id);
    boolean delete(int id);
    List<TestingTo> getAll();
    TestingTo save(T test);
    List<TestingTo> getAllBySampleId(int sampleId);
    boolean setParamOrder(String name, List<String> orderList);
    List<String> getParamOrder(String name);



}
