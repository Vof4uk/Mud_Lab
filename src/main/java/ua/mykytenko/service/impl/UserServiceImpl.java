package ua.mykytenko.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.mykytenko.entities.user.User;
import ua.mykytenko.repository.UserRepository;
import ua.mykytenko.service.UserService;
import ua.mykytenko.util.exceptions.MyIllegalStateException;
import ua.mykytenko.util.exceptions.NotFoundException;

import java.util.List;
import java.util.stream.Collectors;

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
        if(user == null) throw new NotFoundException("Failed to find user");
        return user;
    }

    @Override
    public User saveNew(User user) {
        if(!user.isNew()) throw new MyIllegalStateException("Could not save/update");
        User user1 = repository.save(user);
        if(user1 == null)
            throw new MyIllegalStateException("Could not save/update User");
        return  user1;
    }

    @Override
    public void update(User user) {
        if(user.isNew()) throw new MyIllegalStateException("Could not save/update User");
        if(repository.save(user) == null)
            throw new NotFoundException("Could not save/update User");
    }

    @Override
    public void delete(int userId) {
        if(!repository.delete(userId))
            throw new NotFoundException("Failed to delete User");
    }

    @Override
    public List<User> getAll() {
        return repository.getAll().stream()
                .sorted((u1, u2) -> Integer.compare(u1.getId(), u2.getId()))
                .collect(Collectors.toList());
    }
}
