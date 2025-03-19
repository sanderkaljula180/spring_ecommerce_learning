package com.example.demo.model.dto;

import lombok.Data;

@Data
public class UserDTO {

    private String username;
    private String password;
    private String email;

    public UserDTO() {
    }

    public UserDTO(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
