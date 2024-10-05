package com.example.demo.model;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@Transactional
public class UserEntityTest {


    @Autowired
    private EntityManager entityManager;

    @Test
    public void whenSavingValidUser_thenSuccess() {

        // Arrange
        User user = new User();
        user.setUsername("john_doe");
        user.setPassword("securepassword");
        user.setEmail("john@example.com");

        // Act
        entityManager.persist(user);
        entityManager.flush();

        // Assert
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void whenSavingUserWithoutUsername_thenFail() {
        // Arrange
        User user = new User();
        user.setPassword("password1");
        user.setEmail("john.doe@example.com");



        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(user);
            entityManager.flush();
        });
    }


    @Test
    public void whenSavingUserWithDuplicateUsername_thenFail() {
        // Arrange
        User user1 = new User();
        user1.setUsername("john_doe");
        user1.setPassword("password1");
        entityManager.persist(user1);

        User user2 = new User();
        user2.setUsername("john_doe");
        user2.setPassword("password2");

        // Act & Assert
        assertThrows(ConstraintViolationException.class, () -> {
            entityManager.persist(user2);
            entityManager.flush();
        });
    }
}
