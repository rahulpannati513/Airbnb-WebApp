package com.rahul.repo;

import com.rahul.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

 public  User findByUsername(String username);

 boolean existsByUsername(String username);

 boolean existsByEmail(String email);

 User findByEmail(String email);
}
