package ua.mykytenko.repository.mock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Repository;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.repository.SampleRepository;
import ua.mykytenko.repository.SampleFamilyRepository;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ua.mykytenko.entities.samples.Description.*;

/**
 * Created by Микитенко on 20.10.2016.
 */
@DependsOn("familyRepo")
@Repository
public class MockSampleRepositoryImpl implements SampleRepository{

    private SampleFamilyRepository familyRepo;

    @Autowired
    public MockSampleRepositoryImpl(SampleFamilyRepository familyRepo) {
        this.familyRepo = familyRepo;
    }

    private static final AtomicInteger id = new AtomicInteger(1);
    private final Map<Integer, Sample> dataBase = new ConcurrentHashMap<>();
    private boolean initiated = false;

    private void initiate(){
//        familyRepo = ServletUtil.getSpringContext().getBean(MockSampleFamilyRepoImpl.class);

        Applications application1 = Applications.parse("viscosifier");
        Applications application2 = Applications.parse("thinner; defloc");
        Components composition1 = Components.parse("100% - xanthan gum");
        Components composition2 = Components.parse("90% - xanthan gum;" + "\n" +
                                                       "10% - caustic pot." );
        Sample s1 = new Sample("polymers", "barazan D", 0.9f, LocalDate.now(),application1, composition1,
                new Vendor("garage1"), new Manufacturer("bazar1"), "some notes");
        Sample s2 = new Sample("ligno", "УЩР", 1.1f, LocalDate.now(),application2, composition2,
                new Vendor("garage2"), new Manufacturer("olx.com"), "other notes");
        save(s1);
        save(s2);
    }
    @Override
    public List<Sample> getAll() {
        if(!initiated){
            initiate();
            initiated = true;
        }

        List<Sample> list = new ArrayList<>();
        Collections.addAll(list, dataBase.values().toArray(new Sample[1]));
        return list;
    }

    @Override
    public Sample save(Sample sample) {
        Integer curId;
        if (sample.isNew()) {
            curId = generateId();
            sample.setId(curId);
        }
        sample.setFamilyId(familyRepo.peakId(sample.getFamilyName()));
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
