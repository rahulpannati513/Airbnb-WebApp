package com.rahul.security.service;

import com.rahul.Exception.UserNotFoundException;
import com.rahul.model.User;
import com.rahul.repo.UserRepository;
import com.rahul.response.AuthResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImplementation  implements  UserService{


    private UserRepository userRepo;

    private  JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);


    @Transactional
    public ResponseEntity<?> saveUser(User user) {
        // Check if user already exists
        if (userRepo.findByUsername(user.getUsername()) != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User Already Exists with username " + user.getUsername());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        String token = jwtService.generateToken(user.getUsername());
         new AuthResponse(token);
        return new ResponseEntity<>(token, HttpStatus.CREATED);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws  UserNotFoundException {
        String username = jwtService.extractUserName(jwt);
        User user = userRepo.findByUsername(username);
        if(user == null){
            throw new UserNotFoundException("User Not Found with email id : "+ username);
        }
        return user;
    }
}
