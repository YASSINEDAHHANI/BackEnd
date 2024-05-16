package com.example.backendsp.DAO.repo;

import com.example.backendsp.DAO.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
    public User findByEmail(String email);
}
