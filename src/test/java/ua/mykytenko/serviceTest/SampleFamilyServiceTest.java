package ua.mykytenko.serviceTest;

import ua.mykytenko.entities.samples.SampleFamily;
import ua.mykytenko.service.SampleFamilyService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.SampleFamilyTestData.NEW_FAMILY;
import static ua.mykytenko.SampleFamilyTestData.FAMILIES;

/**
 * Created by Микитенко on 22.11.2016.
 */
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
