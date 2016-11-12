package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.repository.SampleFamilyRepository;
import ua.mykytenko.service.SampleFamilyService;

import java.util.List;

/**
 * Created by Микитенко on 26.10.2016.
 */
@Service
public class SampleFamilyServiceImpl implements SampleFamilyService {

    final
    SampleFamilyRepository repository;

    @Autowired
    public SampleFamilyServiceImpl(SampleFamilyRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<SampleFamily> getAll() {
        return repository.getAll();
    }

    @Override
    public SampleFamily create(SampleFamily family) {
        return repository.create(family.getName(), family.getInitialId());
    }

    public SampleFamily get(int id){
        return repository.get(id);
    }

    @Override
    public void delete(int id) {
        repository.delete(id);
    }
}
