package ua.mykytenko.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import ua.mykytenko.Profiles;
import ua.mykytenko.entities.user.User;
import ua.mykytenko.repository.UserRepository;

import java.util.Collection;

@Repository
@Profile(Profiles.JPA)
public class JpaUserRepository implements UserRepository{
    @Override
    public User get(int id) {
        return null;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public boolean delete(int userId) {
        return false;
    }

    @Override
    public Collection<User> getAll() {
        return null;
    }
}
