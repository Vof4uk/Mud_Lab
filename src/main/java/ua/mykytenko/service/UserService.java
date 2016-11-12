package ua.mykytenko.service;

import ua.mykytenko.entities.user.User;

/**
 * Created by Микитенко on 10.11.2016.
 */
public interface UserService {
    User get(int id);

    void delete(int id);

    void save(User user);
}
