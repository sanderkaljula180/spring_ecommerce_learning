package com.example.demo.service;


import com.example.demo.Service.UserServiceImpl;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Test
    void whenSavingValidUser_thenSuccess() {
        // Arrange
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("password1");
        user.setEmail("john@example.com");

        Role userRole = new Role();
        userRole.setName("USER");

        // samas jarjekorras nagu meetod mida testitakse
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(roleRepository.findByName("USER")).thenReturn(Optional.of(userRole));
        when(passwordEncoder.encode("password1")).thenReturn("encoded_password");

        // Act
        User createdUser = userServiceImpl.registerUser(user);

        // Assert
        verify(roleRepository, times(1)).findByName("USER");
        verify(userRepository, times(1)).save(user);
        verify(passwordEncoder, times(1)).encode("password1");

        assertNotNull(createdUser);
        assertEquals("john_doe", createdUser.getUsername());
        assertTrue(createdUser.getRoles().contains(userRole));
        assertEquals("encoded_password", createdUser.getPassword());
    }

}
