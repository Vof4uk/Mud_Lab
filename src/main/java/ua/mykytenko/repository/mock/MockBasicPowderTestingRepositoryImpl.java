package ua.mykytenko.repository.mock;

import org.springframework.stereotype.Repository;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.repository.TestingRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Микитенко on 28.10.2016.
 */
@Repository
public class MockBasicPowderTestingRepositoryImpl implements TestingRepository<BasicPowderTesting> {

    private Map<Integer, BasicPowderTesting> tests = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(0);
    private Map<String, List<String>> paramLists = new ConcurrentHashMap<>();
    {
        BasicPowderTesting testing1 = new BasicPowderTesting(1500, 800, "some polymer", 5.6f, true);
        BasicPowderTesting testing2 = new BasicPowderTesting(1800, 450, "some sort of ligno", 8f, true);
        testing1.setSampleId(1);
        testing2.setSampleId(2);
        save(testing1);
        save(testing2);
        setParametersList("BasicPowderTesting", Arrays.asList("Specific gravity", "Appearence"));
    }

    public BasicPowderTesting save(BasicPowderTesting testing){
        if(testing.isNew()){
            testing.setId(id.incrementAndGet());
            tests.put( testing.getId(), testing);
        }
        else if(!tests.containsKey(testing.getId())) return null;
        else{
            tests.put(testing.getId(), testing);
        }
        return testing;
    }

    @Override
    public BasicPowderTesting get(int id) {
        return tests.get(id);
    }

    @Override
    public boolean delete(int id) {
        return tests.remove(id) != null;
    }

    @Override
    public List<BasicPowderTesting> getAll() {
        return tests.values().stream().collect(Collectors.toList());
    }

    @Override
    public List<BasicPowderTesting> getAllBySampleId(int sampleId) {
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
