package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.repository.TestingRepository;
import ua.mykytenko.service.TestingService;
import ua.mykytenko.to.TestingTo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Service
public class BasicPowderTestsServiceImpl implements TestingService<BasicPowderTesting> {
    //TODO inject translation

    @Autowired
    TestingRepository<BasicPowderTesting> repository;

    @Override
    public TestingTo get(int id) {
        BasicPowderTesting t = repository.get(id);
        TestingTo to = new TestingTo(t);
        to.setOrder(repository.getParametersList(t.getEntityName()));
        return to;
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public List<TestingTo> getAll() {
        return convertToTOList(repository.getAll());
    }

    @Override
    public TestingTo save(BasicPowderTesting test) {
        return new TestingTo(repository.save(test));
    }

    @Override
    public List<TestingTo> getAllBySampleId(int sampleId) {
        return convertToTOList(repository.getAllBySampleId(sampleId));
    }

    @Override
    public boolean setParamOrder(String name, List<String> orderList) {
        return repository.setParametersList(name, orderList);
    }

    @Override
    public List<String> getParamOrder(String name) {
        return repository.getParametersList(name);
    }

    private List<TestingTo> convertToTOList(List<BasicPowderTesting> tests){
        Map<String, List<String>> listMap = tests.stream()
                .map(e -> e.getClass().getSimpleName())
                .distinct()
                .collect(HashMap<String, List<String>>::new,
                        (map, name) -> map.put(name, repository.getParametersList(name)),
                        (m, n) -> {});
        return tests.stream()
                .map(TestingTo::new)
                .peek(to ->to.setOrder(listMap.get(to.getEntityName())))
                .collect(Collectors.toList());
    }
}
