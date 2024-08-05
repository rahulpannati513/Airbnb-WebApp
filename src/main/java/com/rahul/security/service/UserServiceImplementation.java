package com.rahul.security.service;

import com.rahul.Exception.UserNotFoundException;
import com.rahul.model.User;
import com.rahul.repo.UserRepository;
import com.rahul.response.AuthResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImplementation  implements  UserService{

    private final UserRepository userRepo;

    private final  JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserServiceImplementation(UserRepository userRepo, JwtService jwtService) {
        this.userRepo = userRepo;
        this.jwtService = jwtService;
    }

    @Transactional
    public ResponseEntity<?> saveUser(User user) {
        // Check if username already exists
        if (userRepo.findByUsername(user.getUsername()) != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User already exists with username: " + user.getUsername());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        // Check if email already exists
        if (userRepo.findByEmail(user.getEmail()) != null) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User already exists with email: " + user.getEmail());
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
        String token = jwtService.generateToken(user.getUsername());

        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.CREATED);
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

    @Override
    public ResponseEntity<?> loginUser(String username, String password) {
        User user = userRepo.findByUsername(username);
        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }
        String token = jwtService.generateToken(username);
        return new ResponseEntity<>(new AuthResponse(token), HttpStatus.OK);
    }


}


