package com.example.backendsp.Requests;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SingupRequest {
    private String name;
    private String email;
    private String password;
    private String Confpassword;

}
