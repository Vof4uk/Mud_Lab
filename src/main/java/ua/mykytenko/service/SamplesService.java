package ua.mykytenko.service;

import ua.mykytenko.entities.samples.Sample;

import java.util.List;

/**
 * Created by Микитенко on 20.10.2016.
 */
public interface SamplesService {
    List<Sample> getAll();
    Sample saveNew(Sample sample);
    Sample update(Sample sample);
    void delete(int sampleId);
    Sample get(int sampleId);
}
