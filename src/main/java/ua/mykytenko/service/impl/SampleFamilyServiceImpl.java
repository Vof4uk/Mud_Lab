package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.repository.SampleFamilyRepository;
import ua.mykytenko.service.SampleFamilyService;
import ua.mykytenko.util.exceptions.MyIllegalStateException;
import ua.mykytenko.util.exceptions.NotFoundException;

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
        List<SampleFamily> list = repository.getAll();
        if(list == null || list.isEmpty()) throw new NotFoundException("failed to find any Family [getAll]");
        return list;
    }

    @Override
    public SampleFamily saveNew(SampleFamily family) {
        if(!family.isNew()) throw new MyIllegalStateException("failed to [create] Family");
        SampleFamily f = repository.save(family);
        if(f == null) throw new NotFoundException("failed to [create] Family");
        return f;
    }

    @Override
    public void update(SampleFamily family){
        if(family.isNew()) throw new NotFoundException("failed to [update] family with id = null");
        SampleFamily family1 = repository.save(family);
        if(family1 == null) throw new NotFoundException("failed to [update] family");
    }

    public SampleFamily get(int id){
        SampleFamily family = repository.get(id);
        if(family == null) throw new NotFoundException("failed to find Family with id " + id + " [get]");
        return family;
    }

    @Override
    public void delete(int id) {
        if (!repository.delete(id)) throw new NotFoundException("failed to find Family with id " + id + " [delete]");
    }
}
