package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.user.User;
import ua.mykytenko.repository.UserRepository;
import ua.mykytenko.service.UserService;
import ua.mykytenko.util.exceptions.MyIllegalStateException;
import ua.mykytenko.util.exceptions.NotFoundExceprion;

/**
 * Created by Микитенко on 10.11.2016.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository repository;

    @Override
    public User get(int id) {
        User user = repository.get(id);
        if(user == null) throw new NotFoundExceprion("Failed to find user");
        return user;
    }

    @Override
    public void save(User user) {
        if(repository.save(user) == null)
            throw new MyIllegalStateException("Could not save/update User");
    }

    @Override
    public void delete(int userId) {
        if(!repository.delete(userId))
            throw new NotFoundExceprion("Failed to delete User");
    }
}
