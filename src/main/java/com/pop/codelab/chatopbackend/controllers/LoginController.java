package com.pop.codelab.chatopbackend.controllers;


import com.pop.codelab.chatopbackend.services.JWTService;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

//    @GetMapping("/user")
//    public String getUser() {
//        return "Welcome, User";
//    }
//
//    @GetMapping("/admin")
//    public String getAdmin() {
//        return "Welcome, Admin";
//    }

    private JWTService jwtService;

    public LoginController(JWTService jwtService) {
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public String getToken(Authentication authentication) {
        String token = jwtService.generateToken(authentication);
        return token;
    }

}
