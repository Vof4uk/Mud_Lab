package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.repository.TestingRepository;
import ua.mykytenko.service.BasicPowderTestingService;
import ua.mykytenko.service.TestingService;
import ua.mykytenko.to.TestingTo;
import ua.mykytenko.util.exceptions.MyIllegalStateException;
import ua.mykytenko.util.exceptions.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Микитенко on 29.10.2016.
 */
@Service
public class BasicPowderTestingServiceImpl implements BasicPowderTestingService{
    //TODO inject translation

    @Autowired
    TestingRepository<BasicPowderTesting> repository;

    @Override
    public TestingTo get(int id) {
        BasicPowderTesting t = repository.get(id);
        if(t == null) throw new NotFoundException("no powdertesting with such id");
        TestingTo to = new TestingTo(t);
        to.setOrder(repository.getParametersList(t.getEntityName()));
        return to;
    }

    @Override
    public void delete(int id) {
        if(!repository.delete(id)) throw new NotFoundException("No such testing to delete");
    }

    @Override
    public List<TestingTo> getAll() {
        List<BasicPowderTesting> list = repository.getAll();
        if(list == null || list.isEmpty()) throw new NotFoundException("No powdertestig found[getall]");
        return convertToTOList(list);
    }

    @Override
    public TestingTo saveNew(BasicPowderTesting test) {
        if(!test.isNew()) throw new MyIllegalStateException("must be new");
        BasicPowderTesting t = repository.save(test);
        if(t == null) throw new RuntimeException("failed to create new powdertesting");
        return new TestingTo(t);
    }

    @Override
    public void update(BasicPowderTesting test) {
        if(test.isNew()) throw new MyIllegalStateException("must not be new");
        if(repository.save(test) == null ) throw new NotFoundException("failed to update");
    }

    @Override
    public List<TestingTo> getAllBySampleId(int sampleId) {
        List<BasicPowderTesting> list = repository.getAllBySampleId(sampleId);
        if (list == null || list.isEmpty()) throw new NotFoundException("No powder tests tied to this sample");
        return convertToTOList(list);
    }

    @Override
    public void setParamOrder(String name, List<String> orderList) {
        if(!repository.setParametersList(name, orderList)) throw new RuntimeException("failed to save parameter list");
    }

    @Override
    public List<String> getParamOrder(String name) {
        List<String> list = repository.getParametersList(name);
        if(list == null || list.isEmpty()) throw new NotFoundException("no such param list");
        return list;
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
