package com.example.demo.utility;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;
import com.example.demo.repository.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class UserComponents {

    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserComponents(RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User processUserRegistration(UserDTO userDTO, String userRole) {
        User user = new User();

        Role settingUserRole = userRoleHandler(userRole);

        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setRoles(Collections.singleton(settingUserRole));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        return user;
    }

    public Role userRoleHandler(String userRole) {
       return roleRepository.findByName(userRole)
               .orElseGet(() -> {
                  Role newUserRole = new Role();
                  newUserRole.setName(userRole);
                  return roleRepository.save(newUserRole);
               });
    }
}
