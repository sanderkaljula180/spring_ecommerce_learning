package com.example.demo.repository;

import com.example.demo.model.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    // Do I really need three test that kinda function the same?

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

        // Assert
        assertTrue(foundUser.isPresent());
        assertEquals("alice", foundUser.get().getUsername());
    }

    @Test
    @DisplayName("Test existsByUsername")
    public void existsByUsername() {
        // Arrange
        User user = new User();
        user.setUsername("bob");
        user.setPassword("password");
        user.setEmail("email@example.com");
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByUsername("bob");

        // Assert
        assertTrue(exists);
    }

    @Test
    @DisplayName("Test existsByEmail")
    public void existsByEmail() {

        // Arrange
        User user = new User();
        user.setUsername("timo");
        user.setPassword("password");
        user.setEmail("example@email.com");
        userRepository.save(user);

        // Act
        boolean exists = userRepository.existsByEmail("example@email.com");

        // Assert
        assertTrue(exists);

    }
}
