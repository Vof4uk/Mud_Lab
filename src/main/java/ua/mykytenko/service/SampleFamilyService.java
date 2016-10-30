package ua.mykytenko.service;

import ua.mykytenko.entities.samples.SampleFamily;

import java.util.List;

/**
 * Created by Микитенко on 26.10.2016.
 */
public interface SampleFamilyService {
    List<SampleFamily> getAll();
    SampleFamily create(SampleFamily family);
    void delete(int id);
    SampleFamily get(int id);
}
