package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("Test findByUsername")
    public void testFindByUsername() {

        // Arrange
        User user = new User();
        user.setUsername("alice");
        user.setPassword("password");
        user.setEmail("alice@example.com");
        userRepository.save(user);
        System.out.println(user);

        // Act
        Optional<User> foundUser = userRepository.findByUsername("alice");

        // Act
        assertTrue(foundUser.isPresent());
        assertEquals("alice", foundUser.get().getUsername());
    }
}
