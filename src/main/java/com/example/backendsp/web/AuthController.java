package com.example.backendsp.web;

import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.UserRepo;
import com.example.backendsp.Requests.SigninRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody SigninRequest signinReq){
        User user =userRepo.findByEmail(signinReq.getEmail());
        System.out.println(user);
        if (Objects.equals(user.getPassword(), signinReq.getPassword())){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Invalid Credentials", HttpStatus.NOT_FOUND);
        }

    }





}
