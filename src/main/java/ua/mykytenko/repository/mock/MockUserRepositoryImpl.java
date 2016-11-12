package ua.mykytenko.repository.mock;

import org.springframework.stereotype.Repository;
import ua.mykytenko.entities.user.User;
import ua.mykytenko.entities.user.UserRole;
import ua.mykytenko.repository.UserRepository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Микитенко on 10.11.2016.
 */
@Repository
public class MockUserRepositoryImpl implements UserRepository{
    private Map<Integer, User> users = new ConcurrentHashMap<>();
    private AtomicInteger id = new AtomicInteger(1);

    {
        User user = new User("unauthorized");
        save(user);
        user = new User("assist", UserRole.LAB_ASSIST);
        save(user);
        user = new User("chiefLab", UserRole.LAB_CHIEF);
        save(user);
        user = new User("boss", UserRole.BOSS);
        save(user);
        user = new User("admin", UserRole.ADMIN);
        save(user);
    }

    @Override
    public User get(int id) {
        return users.get(id);
    }

    @Override
    public User save(User user) {
        if(user.isNew()) user.setId(id.getAndIncrement());
        else if(!users.containsKey(user.getId())) return null;
        return users.put(user.getId(), user);
    }

    @Override
    public boolean delete(int id) {
        return users.remove(id) != null;
    }
}
