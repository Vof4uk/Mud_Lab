package ua.mykytenko.repository.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.repository.SampleFamilyRepository;
import ua.mykytenko.repository.SampleRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ua.mykytenko.entities.samples.Description.*;

@DependsOn("familyRepo")
@Repository
@Profile(Profiles.IN_MEMORY_MAP)
public class MockSampleRepositoryImpl implements SampleRepository{

    private SampleFamilyRepository familyRepo;

    @Autowired
    public MockSampleRepositoryImpl(SampleFamilyRepository familyRepo) {
        this.familyRepo = familyRepo;
        initiate();
    }

    private final AtomicInteger id = new AtomicInteger(1);
    private final Map<Integer, Sample> dataBase = new ConcurrentHashMap<>();

    private void initiate(){
        Applications application1 = Applications.parse("viscosifier");
        Applications application2 = Applications.parse("thinner; defloc");
        Components composition1 = Components.parse("100% - xanthan gum");
        Components composition2 = Components.parse("90% - xanthan gum;" + "\n" +
                                                       "10% - caustic pot." );
        SampleFamily sf1 = new SampleFamily("polymers", 5000);
        SampleFamily sf2 = new SampleFamily("ligno", 8000);
        Sample s1 = new Sample(sf1, "barazan D", 0.9f, LocalDate.now(),application1, composition1,
                new Company("garage1"), new Company("bazar1"), "some notes");
        Sample s2 = new Sample(sf2, "УЩР", 1.1f, LocalDate.now(),application2, composition2,
                new Company("garage2"), new Company("olx.com"), "other notes");
        save(s1);
        save(s2);
    }

    @Override
    public List<Sample> getAll() {
        List<Sample> list = new ArrayList<>();
        Collections.addAll(list, dataBase.values().toArray(new Sample[1]));
        return list;
    }

    @Override
    public Sample save(Sample sample) {
        if (sample.isNew()) {
            sample.setId(generateId());
            sample.setFamilyId(familyRepo.pullId(sample.getFamily().getName()));
        }else if(!dataBase.containsKey(sample.getId()))
            return null;
//        else
//            sample.setFamilyId(familyRepo.peakId(sample.getFamily()));
        dataBase.put(sample.getId(), sample);
        return sample;
    }

    @Override
    public boolean delete(int sampleId) {
        return dataBase.remove(sampleId) != null;
    }

    @Override
    public Sample get(int sampleId) {
        return dataBase.get(sampleId);
    }

    private int generateId(){
        return id.getAndIncrement();
    }
}
