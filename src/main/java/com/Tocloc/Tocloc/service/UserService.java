package com.Tocloc.Tocloc.service;

import com.Tocloc.Tocloc.entities.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long userId);
    User save(User user);
    void deleteById(Long userId);
    User update(Long userId, User userDetails);
}
