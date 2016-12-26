package ua.mykytenko.repository.mock;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.repository.TestingRepository;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Profile(Profiles.IN_MEMORY_MAP)
@Repository
public class MockBasicMudTestingRepositoryImpl implements TestingRepository<BasicMudTesting>{

    private Map<Integer, BasicMudTesting> tests = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);
    private Map<String, List<String>> paramLists = new ConcurrentHashMap<>();

    {
        Map<Integer, Integer> gels1 = new HashMap<>();
        gels1.put(10, 4);gels1.put(60, 8);gels1.put(600, 15);
        Map<Integer, Integer> gels2 = new HashMap<>();
        gels2.put(10, 5);gels2.put(60, 10);gels2.put(600, 21);

        Map<Integer, Integer> rheo1 = new HashMap<>();
        rheo1.put(600, 45);rheo1.put(300, 31);rheo1.put(200, 25);rheo1.put(100, 19);rheo1.put(6, 5);rheo1.put(3, 4);
        Map<Integer, Integer> rheo2 = new HashMap<>();
        rheo2.put(600, 33);rheo2.put(300, 21);rheo2.put(200, 12);rheo2.put(100, 10);rheo2.put(6, 3);rheo2.put(3, 2);

        BasicMudTesting testing1 = new BasicMudTesting(0.5f, 1.03f, 24, 10.1f, 8.9f, rheo1, gels2);
        BasicMudTesting testing2 = new BasicMudTesting(2f, 1.03f, 16, 15f, 7.2f, rheo2, gels1);
        testing1.setSampleId(1);
        testing2.setSampleId(2);

        save(testing1);
        save(testing2);


        setParametersList("BasicMudTesting", Arrays.asList("Mud weight", "Viscosity", "Fluid loss"));

//        paramLists.put()
    }

    @Override
    public BasicMudTesting get(int id) {
        return tests.get(id);
    }

    @Override
    public boolean delete(int id) {
        return tests.remove(id) != null;
    }

    @Override
    public List<BasicMudTesting> getAll() {
        return tests.values().stream().collect(Collectors.toList());
    }

    @Override
    public BasicMudTesting save(BasicMudTesting test) {
        if(test.isNew()){
            test.setId(id.incrementAndGet());
            tests.put( test.getId(), test);
        }
        else if(!tests.containsKey(test.getId())) return  null;
        else{
            tests.put(test.getId(), test);
        }
        return test;
    }

    @Override
    public List<BasicMudTesting> getAllBySampleId(int sampleId) {
        return tests.values().stream()
                .filter(t -> t != null && t.getSampleId() == sampleId)
                .collect(Collectors.toList());
    }

    @Override
    public boolean setParametersList(String name, List<String> paramList) {
        return paramLists.put(name, paramList) != null;
    }

    @Override
    public List<String> getParametersList(String name) {
        return paramLists.get(name);
    }
}
