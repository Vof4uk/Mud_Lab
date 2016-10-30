package ua.mykytenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.repository.TestingRepository;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Service
public class BasicPowderTestsServiceImpl implements TestingService<BasicPowderTesting> {

    @Autowired
    TestingRepository<BasicPowderTesting> repository;

    @Override
    public BasicPowderTesting get(int id) {
        return repository.get(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public List<BasicPowderTesting> getAll() {
        return repository.getAll();
    }

    @Override
    public BasicPowderTesting save(BasicPowderTesting test) {
        return repository.save(test);
    }

    @Override
    public List<BasicPowderTesting> getAllBySampleId(int sampleId) {
        return repository.getAllBySampleId(sampleId);
    }
}
