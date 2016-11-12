package ua.mykytenko.entities.user;

import ua.mykytenko.entities.NamedEntity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Микитенко on 06.11.2016.
 */
public class User extends NamedEntity{

    private Set<UserRole> roles = new HashSet<>();;

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

    public void addRoles(UserRole role){
        roles.add(role);
    }

    public void removeRole(UserRole role){
        roles.remove(role);
    }
}
