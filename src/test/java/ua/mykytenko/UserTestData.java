package ua.mykytenko;

import ua.mykytenko.entities.user.User;
import ua.mykytenko.entities.user.UserRole;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Микитенко on 12.11.2016.
 */
public class UserTestData {
    public static final User USER_1 = new User("unauthorized");
    public static final User USER_2 = new User("assist",UserRole.LAB_ASSIST);
    public static final User USER_3 = new User("chiefLab", UserRole.LAB_CHIEF);
    public static final User USER_4 = new User("boss", UserRole.BOSS);
    public static final User USER_5 = new User("admin", UserRole.ADMIN);

    public static final List<User> USERS = Arrays.asList(USER_1, USER_2, USER_3, USER_4, USER_5);

    public static final User NEW_USER = new User("boss2", UserRole.BOSS);
}
