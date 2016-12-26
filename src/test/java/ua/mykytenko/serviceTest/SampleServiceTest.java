package ua.mykytenko.serviceTest;

import org.junit.Ignore;
import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.service.SamplesService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.SampleTestData.*;
@Ignore
public class SampleServiceTest extends AbstractCrudServiceTest {
    {
        clazz = Sample.class;
        newEntity = NEW_SAMPLE;
        entities = SAMPLES;
    }

    @Override
    public void manageEntities() {
        compareEntity = COMPARE_SAMPLE;
    }

    @PostConstruct
    public void setService() {
        service = springContext.getBean(SamplesService.class);
    }
}
