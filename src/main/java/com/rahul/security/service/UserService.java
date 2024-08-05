package com.rahul.security.service;

import com.rahul.Exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import com.rahul.model.User;

public interface UserService {

    public ResponseEntity<?> saveUser(User user) throws UserNotFoundException;

    public User findUserProfileByJwt(String jwt) throws UserNotFoundException;

    ResponseEntity<?> loginUser(String username, String password);
}
