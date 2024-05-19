package com.example.backendsp.web;

import com.example.backendsp.DAO.entities.User;
import com.example.backendsp.DAO.repo.UserRepo;
import com.example.backendsp.Requests.SigninRequest;
import com.example.backendsp.Requests.SingupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody SingupRequest singupRequest){
            if (!Objects.equals(singupRequest.getPassword(), singupRequest.getConfpassword())) {
                return new ResponseEntity<>("Passwords do not match", HttpStatus.BAD_REQUEST);
            }

            User UserExist = userRepo.findByEmail(singupRequest.getEmail());
            if (UserExist != null) {
                return new ResponseEntity<>("Email already in use", HttpStatus.CONFLICT);
            }
            User newUser = User.builder()
                    .name(singupRequest.getName())
                    .email(singupRequest.getEmail())
                    .password(singupRequest.getPassword())
                    .Role("USER")
                    .build();
            userRepo.save(newUser);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        }
    }


