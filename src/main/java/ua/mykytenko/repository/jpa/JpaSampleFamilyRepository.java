package ua.mykytenko.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.repository.SampleFamilyRepository;

import java.util.List;

@Repository
@Profile(Profiles.JPA)
public class JpaSampleFamilyRepository implements SampleFamilyRepository{
    @Override
    public List<SampleFamily> getAll() {
        return null;
    }

    @Override
    public SampleFamily get(int id) {
        return null;
    }

    @Override
    public SampleFamily save(SampleFamily family) {
        return null;
    }

    @Override
    public Integer pullId(String familyName) {
        return null;
    }

    @Override
    public Integer peakId(String familyName) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
