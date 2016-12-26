package ua.mykytenko.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.repository.SampleRepository;

import java.util.List;

@Repository
@Profile(Profiles.JPA)
public class JpaSampleRepository implements SampleRepository{
    @Override
    public List<Sample> getAll() {
        return null;
    }

    @Override
    public Sample save(Sample sample) {
        return null;
    }

    @Override
    public boolean delete(int sampleId) {
        return false;
    }

    @Override
    public Sample get(int sampleId) {
        return null;
    }
}
