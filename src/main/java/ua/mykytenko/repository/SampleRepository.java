package ua.mykytenko.repository;

import ua.mykytenko.entities.samples.Sample;

import java.util.List;

/**
 * Created by Микитенко on 20.10.2016.
 */

public interface SampleRepository {
    List<Sample> getAll();
    Sample save(Sample sample);
    boolean delete(int sampleId);
    Sample get(int sampleId);

}
