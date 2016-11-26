package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.repository.SampleRepository;
import ua.mykytenko.service.SamplesService;
import ua.mykytenko.util.exceptions.MyIllegalStateException;
import ua.mykytenko.util.exceptions.NotFoundException;

import java.util.List;

/**
 * Created by Микитенко on 20.10.2016.
 */
@Service
public class SampleServiceImpl implements SamplesService {

    @Autowired
    SampleRepository repository;

    @Override
    public Sample get(int sampleId) {
        Sample sample = repository.get(sampleId);
        if (sample == null) throw new NotFoundException("No such sample found [get]");
        return sample;
    }

    @Override
    public List<Sample> getAll() {
        List<Sample> list = repository.getAll();
        if(list == null || list.isEmpty()) throw new NotFoundException("repository is empty");
        return list;
    }

    @Override
    public Sample saveNew(Sample sample) {
        if(!sample.isNew()) throw new MyIllegalStateException("Sample must be new one[save]");
        Sample s = repository.save(sample);
        if (s == null)  throw new NotFoundException("repository is empty [save]");
        return s;
    }

    @Override
    public void update(Sample sample) {
        if (sample.isNew()) throw new MyIllegalStateException("id not provided for update");
        Sample s = repository.save(sample);
        if (s == null)  throw new NotFoundException("repository is empty [update]");
    }

    @Override
    public void delete(int sampleId) {
        if(!repository.delete(sampleId)) throw new NotFoundException("repository is empty [delete]");
    }
}
