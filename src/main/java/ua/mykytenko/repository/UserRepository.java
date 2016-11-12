package ua.mykytenko.repository;

import ua.mykytenko.entities.user.User;

/**
 * Created by Микитенко on 10.11.2016.
 */
public interface UserRepository {

    User get(int id);

    User save(User user);

    boolean delete(int userId);
}
