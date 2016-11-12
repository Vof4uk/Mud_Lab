package ua.mykytenko.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import ua.mykytenko.entities.user.UserRole;
import ua.mykytenko.service.UserService;
import ua.mykytenko.util.exceptions.ForbiddenRequestException;

import java.util.Set;

/**
 * Created by Микитенко on 10.11.2016.
 */
public abstract class AbstractController {

    @Autowired
    protected UserService service;

    protected void checkUserForAccess(int userId, UserRole... roles){
        Set<UserRole> roleSet = service.get(userId).getRoles();
        for (UserRole role: roles)
        {
            if(roleSet.contains(role)) return;
        }
        throw new ForbiddenRequestException("userId #" + userId + "not allowed");
    }
}
