package com.example.backendsp.DAO.entities;

import jakarta.persistence.*;
import lombok.*;

import com.example.backendsp.DAO.enums.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@Data
@Builder
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;

    @OneToOne(mappedBy = "user")
    private Cart cart ;
    @Enumerated(EnumType.STRING)
    private Role role;
    public User(){
        this.setRole(Role.ADMIN);

    }

}
