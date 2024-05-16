package com.example.backendsp.DAO.entities;

import com.example.backendsp.DAO.enums.Role;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Admin extends User{
    public Admin(){
        super();
        this.setRole(Role.ADMIN);
    }


}
