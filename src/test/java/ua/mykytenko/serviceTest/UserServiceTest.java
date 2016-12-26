package ua.mykytenko.serviceTest;


import org.junit.Ignore;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.user.User;
import ua.mykytenko.service.UserService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.UserTestData.NEW_USER;
import static ua.mykytenko.UserTestData.USERS;

@Ignore
public class UserServiceTest extends AbstractCrudServiceTest {

    {
        clazz = User.class;
        newEntity = NEW_USER;
        entities = USERS;
    }

    @PostConstruct
    public void setService() {
        service = springContext.getBean(UserService.class);
    }
}
