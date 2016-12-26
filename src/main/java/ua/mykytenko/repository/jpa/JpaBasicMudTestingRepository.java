package ua.mykytenko.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.repository.TestingRepository;

import java.util.List;

@Repository
@Profile(Profiles.JPA)
public class JpaBasicMudTestingRepository implements TestingRepository<BasicMudTesting> {
    @Override
    public BasicMudTesting get(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<BasicMudTesting> getAll() {
        return null;
    }

    @Override
    public BasicMudTesting save(BasicMudTesting test) {
        return null;
    }

    @Override
    public List<BasicMudTesting> getAllBySampleId(int sampleId) {
        return null;
    }

    @Override
    public boolean setParametersList(String name, List<String> paramList) {
        return false;
    }

    @Override
    public List<String> getParametersList(String name) {
        return null;
    }
}
