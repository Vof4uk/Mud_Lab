package ua.mykytenko.web.controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.mykytenko.service.UserService;

/**
 * Created by Микитенко on 10.11.2016.
 */
@Controller
public class UserController {

    @Autowired
    private UserService service;



}
