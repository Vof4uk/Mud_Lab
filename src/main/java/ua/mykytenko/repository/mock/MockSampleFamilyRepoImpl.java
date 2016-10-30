package ua.mykytenko.repository.mock;

import org.springframework.stereotype.Repository;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.repository.SampleFamilyRepository;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Микитенко on 26.10.2016.
 */
@Repository("familyRepo")
public class MockSampleFamilyRepoImpl implements SampleFamilyRepository {

    private final Map<SampleFamily, AtomicInteger> inFamilyIdCounters = new ConcurrentHashMap<>();
    private final Map<Integer, SampleFamily> families = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);
    {
        create("polymers", 5000);
        create("ligno", 8000);
        create("other", 2000);
    }

    public MockSampleFamilyRepoImpl() {
    }

    @Override
    public List<SampleFamily> getAll() {
        return inFamilyIdCounters.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public SampleFamily create(String familyName, int initialId) {
        SampleFamily family = new SampleFamily(familyName, initialId);
        inFamilyIdCounters.put(family, new AtomicInteger(initialId));
        families.put(id.incrementAndGet(), family);
        return family;
    }

    @Override
    public SampleFamily get(int id) {
        return families.get(id);
    }

    @Override
    public Integer pullId(String familyName) {
        AtomicInteger id = getFamilyAtomicIdByName(familyName);
        if(Objects.nonNull(id)) return id.incrementAndGet();
        return null;
    }

    @Override
    public Integer peakId(String familyName) {
        AtomicInteger id = getFamilyAtomicIdByName(familyName);
        if(Objects.nonNull(id)) return id.get();
        return null;
    }

    private AtomicInteger getFamilyAtomicIdByName(String familyName){
        for (Map.Entry<SampleFamily, AtomicInteger> entry: inFamilyIdCounters.entrySet()){
            if(entry.getKey().getName().equals(familyName)) return entry.getValue();
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        SampleFamily family = families.get(id);
        families.remove(id);
        return inFamilyIdCounters.remove(family) != null;
    }
}
