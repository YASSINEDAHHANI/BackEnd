package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.User;

import java.util.List;

public interface UserServices {
    List<User> getAllUsers();
    User getUserById(Long Id);
    void saveUser(User user);
    void deleteUser(Long Id);
}
