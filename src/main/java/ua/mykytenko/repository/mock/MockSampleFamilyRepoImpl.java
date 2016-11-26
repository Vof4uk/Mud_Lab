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

    private final Map<Integer, AtomicInteger> inFamilyIdCounters = new ConcurrentHashMap<>();
    private final Map<Integer, SampleFamily> families = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger(0);
    {
        save(new SampleFamily("polymers", 5000));
        save(new SampleFamily("ligno", 8000));
        save(new SampleFamily("other", 2000));
    }

    public MockSampleFamilyRepoImpl() {
    }

    @Override
    public List<SampleFamily> getAll() {
        return families.values().stream().collect(Collectors.toList());
    }

    @Override
    public SampleFamily save(SampleFamily family) {
        if(family.isNew()){
            family.setId(id.incrementAndGet());
            inFamilyIdCounters.put(family.getId(), new AtomicInteger(family.getInitialId()));
            families.put(family.getId(), family);
        }
        else{
            if(!families.containsKey(family.getId())) return null;
            families.put(family.getId(), family);
        }
        return family;
    }

    @Override
    public SampleFamily get(int id) {
        return families.get(id);
    }

    @Override
    public Integer pullId(String familyName) {
        AtomicInteger id = getFamilyAtomicIdByName(familyName);
        if(Objects.nonNull(id)) return id.getAndIncrement();
        return null;
    }

    @Override
    public Integer peakId(String familyName) {
        AtomicInteger id = getFamilyAtomicIdByName(familyName);
        if(Objects.nonNull(id)) return id.get();
        return null;
    }

    private AtomicInteger getFamilyAtomicIdByName(String familyName){
        for (Map.Entry<Integer, SampleFamily> entry: families.entrySet()){
            if(entry.getValue().getName().equals(familyName)) return inFamilyIdCounters.get(entry.getValue().getId());
        }
        return null;
    }

    @Override
    public boolean delete(int id) {
        return inFamilyIdCounters.remove(id) != null && families.remove(id) != null;
    }
}
