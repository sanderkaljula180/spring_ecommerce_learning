package com.example.demo.controller;

import com.example.demo.Service.UserServiceImpl;
import com.example.demo.model.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/registration")
    public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new ResponseEntity<>("User created", HttpStatus.CREATED);
    }

    @PostMapping("/admin-registration")
    public ResponseEntity<String> registerAdminUser(@RequestBody @Valid UserDTO userDTO) {
        userService.registerAdminUser(userDTO);
        return new ResponseEntity<>("Admin user created", HttpStatus.CREATED);
    }

    // USER LOGIN
}
