package ua.mykytenko.entities.user;

import ua.mykytenko.entities.NamedEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User extends NamedEntity{

    private Set<UserRole> roles = new HashSet<>();

    public User() {
    }

    public User(String name, UserRole... roles) {
        super(name);
        this.roles.addAll(Arrays.asList(roles));
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public void addRole(UserRole role){
        if(role == null) return;
        roles.add(role);
    }

    public void removeRole(UserRole role){
        roles.remove(role);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return getId() == (int)user.getId() &&
                Objects.equals(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getRoles());
    }
}
