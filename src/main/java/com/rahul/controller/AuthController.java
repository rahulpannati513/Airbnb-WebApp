package com.rahul.controller;

import com.rahul.Exception.UserNotFoundException;
import com.rahul.model.User;
import com.rahul.request.LoginRequest;
import com.rahul.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<?> registrationHandler(@RequestBody User user) throws UserNotFoundException {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginHandler(@RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest.getUsername(), loginRequest.getPassword());
    }

}