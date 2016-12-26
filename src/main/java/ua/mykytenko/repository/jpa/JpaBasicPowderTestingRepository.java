package ua.mykytenko.repository.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.repository.TestingRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Profile(Profiles.JPA)
public class JpaBasicPowderTestingRepository implements TestingRepository<BasicPowderTesting> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public BasicPowderTesting get(int id) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<BasicPowderTesting> getAll() {
        return null;
    }

    @Override
    public BasicPowderTesting save(BasicPowderTesting test) {
        return null;
    }

    @Override
    public List<BasicPowderTesting> getAllBySampleId(int sampleId) {
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
