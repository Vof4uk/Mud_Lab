package ua.mykytenko.serviceTest;

import ua.mykytenko.entities.tests.powders.BasicPowderTesting;
import ua.mykytenko.service.BasicPowderTestingService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.BasicPowderTestingData.*;

/**
 * Created by Микитенко on 26.11.2016.
 */
public class BasicPowderServiceTest extends AbstractTestingServiceTest{
    {
        clazz = BasicPowderTesting.class;
        newEntity = NEW_TEST;
        entities = TESTS;

        newTO = NEW_TO;
        transferObjects = TOS;
    }
    @PostConstruct
    public void setService() {
        service = springContext.getBean(BasicPowderTestingService.class);
    }
}
