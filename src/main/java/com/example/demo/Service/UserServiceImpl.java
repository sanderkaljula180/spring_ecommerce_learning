package com.example.demo.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.utility.UserComponents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserComponents userComponents;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserComponents userComponents) {
        this.userRepository = userRepository;
        this.userComponents = userComponents;
    }

    @Override
    public void registerUser(UserDTO userDTO) {

        User user = userComponents.processUserRegistration(userDTO, Role.REGULAR_USER_ROLE);
        userRepository.save(user);
    }

    @Override
    public void registerAdminUser(UserDTO userDTO) {

        User user = userComponents.processUserRegistration(userDTO, Role.ADMIN_USER_ROLE);
        userRepository.save(user);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
