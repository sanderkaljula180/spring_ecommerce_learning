package com.example.demo.Service;

import com.example.demo.model.User;
import com.example.demo.model.dto.UserDTO;

import java.util.Optional;

public interface UserService {

    /**
     * Registers a new user with default role and encoded password.
     *
     * @param userDTO The user to register.
     * @return void
     */
    void registerUser(UserDTO userDTO);

    /**
     * Registers a new user with admin role and encoded password.
     *
     * @param userDTO The user to register.
     * @return void
     */
    void registerAdminUser(UserDTO userDTO);

    /**
     * Finds a user by username
     *
     * @param username The username to search for.
     * @return An Optional containing the user if found
     */
    Optional<User> findByUsername(String username);

    /**
     * Checks if a username already exists.
     *
     * @param username The username to check
     * @return True if the username exists, false otherwise
     */
    boolean existsByUsername(String username);


    /**
     * Checks if an email already exists.
     *
     * @param email The email to check.
     * @return True if the email exists, false otherwise.
     */
    boolean existsByEmail(String email);
}
