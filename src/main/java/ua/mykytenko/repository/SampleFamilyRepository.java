package ua.mykytenko.repository;

import ua.mykytenko.entities.samples.SampleFamily;

import java.util.List;

/**
 * Created by Микитенко on 26.10.2016.
 */
public interface SampleFamilyRepository {
    List<SampleFamily> getAll();

    SampleFamily get(int id);

    SampleFamily create(String familyName, int initialId);

    Integer pullId(String familyName);

    Integer peakId(String familyName);

    boolean delete(int id);
}
