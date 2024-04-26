package com.pop.codelab.chatopbackend.user;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pop.codelab.chatopbackend.controllers.CrudController;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController extends CrudController<UserDto> {

    public UserController(UserService userService){
        super(userService);
    }


}
