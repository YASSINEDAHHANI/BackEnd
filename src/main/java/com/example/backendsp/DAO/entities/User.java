package com.example.backendsp.DAO.entities;

import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;

import java.nio.file.FileStore;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    private Cart cart ;
    private String Role;




}
