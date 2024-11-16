package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.entities.User.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User findById(Long userId);
    User save(User user);
    void deleteById(Long userId);
    User update(Long userId, User userDetails);
    UserDetails loadUserByUsername(String email);
    User findByEmail(String email);
}
