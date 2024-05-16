package com.example.backendsp.service;

import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class UserService implements UserServices{
    @Autowired
    public UserRepo userRepo;
    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long Id) {
        Optional<User> userOptional = userRepo.findById(Id);
        return userOptional.orElse(null);
    }

    @Override
    public void saveUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long Id) {
        userRepo.deleteById(Id);
    }
}
