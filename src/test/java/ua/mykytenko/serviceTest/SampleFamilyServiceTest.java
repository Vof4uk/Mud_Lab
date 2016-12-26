package ua.mykytenko.serviceTest;

import org.junit.Ignore;
import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.service.SampleFamilyService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.SampleFamilyTestData.NEW_FAMILY;
import static ua.mykytenko.SampleFamilyTestData.FAMILIES;

@Ignore
public class SampleFamilyServiceTest extends AbstractCrudServiceTest {
    {
        clazz = SampleFamily.class;
        newEntity = NEW_FAMILY;
        entities = FAMILIES;
    }
    @PostConstruct
    public void setService() {
        service = springContext.getBean(SampleFamilyService.class);
    }
}
