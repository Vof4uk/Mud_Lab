package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.repository.TestingRepository;
import ua.mykytenko.service.BasicMudTestingService;
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
public class BasicMudTestingServiceImpl implements BasicMudTestingService{

    @Autowired
    TestingRepository<BasicMudTesting> repository;
    //TODO inject translation

    @Override
    public TestingTo get(int id) {
        BasicMudTesting testing = repository.get(id);
        if(testing == null) throw new NotFoundException("No testing with such ID[get]");
        return new TestingTo(testing);
    }

    @Override
    public void delete(int id) {
        if(!repository.delete(id)) throw new NotFoundException("No testing with such ID [delete]");
    }

    @Override
    public List<TestingTo> getAll() {
        List<BasicMudTesting> list = repository.getAll();
        if(list == null || list.isEmpty()) throw new NotFoundException("no BasicMud testing was found");
        return convertToTOList(list);
    }

    @Override
    public TestingTo saveNew(BasicMudTesting test) {
        if(!test.isNew()) throw new MyIllegalStateException("Id must be absennt to save new testing");
        BasicMudTesting testing = repository.save(test);
        if(testing == null) throw new NotFoundException("failed to save ne testing");
        return new TestingTo(testing);
    }

    @Override
    public void update(BasicMudTesting test) {
        if(test.isNew()) throw new MyIllegalStateException("Id must be present to update new testing");
        BasicMudTesting t = repository.save(test);
        if(t == null) throw new NotFoundException("failed to update testing");
    }

    @Override
    public List<TestingTo> getAllBySampleId(int sampleId){
        List<BasicMudTesting> list = repository.getAllBySampleId(sampleId);
        if(list == null || list.isEmpty()) throw new NotFoundException("No Samples tied with such ID were found");
        return convertToTOList(list);
    }

    @Override
    public void setParamOrder(String name, List<String> orderList) {
        if(!repository.setParametersList(name, orderList)) throw new NotFoundException("No such param order founf");
    }

    @Override
    public List<String> getParamOrder(String name) {
        List<String> list = repository.getParametersList(name);
        if(list == null || list.isEmpty()) throw new NotFoundException("Parameter order is absent. Please specify it");
        return list;
    }

    private List<TestingTo> convertToTOList(List<BasicMudTesting> tests){
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
