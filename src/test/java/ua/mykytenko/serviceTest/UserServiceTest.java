package ua.mykytenko.serviceTest;


import ua.mykytenko.entities.user.User;
import ua.mykytenko.service.UserService;

import javax.annotation.PostConstruct;

import static ua.mykytenko.UserTestData.NEW_USER;
import static ua.mykytenko.UserTestData.USERS;

/**
 * Created by Микитенко on 12.11.2016.
 */
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
