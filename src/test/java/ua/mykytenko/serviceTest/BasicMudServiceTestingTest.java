package ua.mykytenko.serviceTest;

import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.tests.solutions.BasicMudTesting;
import ua.mykytenko.service.BasicMudTestingService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.BasicMudTestingData.*;


@Ignore
public class BasicMudServiceTestingTest extends AbstractTestingServiceTest {
    {
        clazz = BasicMudTesting.class;
        newEntity = NEW_TEST;
        entities = TESTS;

        newTO = NEW_TO;
        transferObjects = TOS;
    }
    @PostConstruct
    public void setService() {
        service = springContext.getBean(BasicMudTestingService.class);
    }
}
