package ua.mykytenko.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.repository.TestingRepository;

import java.util.List;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Service
public class BasicMudTestingServiceImpl implements TestingService<BasicMudTesting> {

    @Autowired
    TestingRepository<BasicMudTesting> repository;

    @Override
    public BasicMudTesting get(int id) {
        return repository.get(id);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public List<BasicMudTesting> getAll() {
        return repository.getAll();
    }

    @Override
    public BasicMudTesting save(BasicMudTesting test) {
        return repository.save(test);
    }

    @Override
    public List<BasicMudTesting> getAllBySampleId(int sampleId) {
        return repository.getAllBySampleId(sampleId);
    }
}
