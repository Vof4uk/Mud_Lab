package ua.mykytenko.serviceTest;

import ua.mykytenko.entities.samples.Sample;
import ua.mykytenko.service.SamplesService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.SampleTestData.*;
/**
 * Created by Микитенко on 19.11.2016.
 */
public class SampleServiceTest extends AbstractCrudServiceTest {
    {
        clazz = Sample.class;
        newEntity = NEW_SAMPLE;
        entities = SAMPLES;
    }

    @PostConstruct
    public void setService() {
        service = springContext.getBean(SamplesService.class);
    }
}
