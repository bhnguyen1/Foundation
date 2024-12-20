package com.fullstackpep.ticketing_project.entity;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


@ExtendWith(MockitoExtension.class)
public class UserTest {
    @Test
    void testDefaultConstructor() {
        User user = new User();
        assertEquals(null, user.getUserId());
        assertEquals(null, user.getUsername());
        assertEquals(null, user.getPassword());
        assertEquals(null, user.getRole());
    }

    @Test
    void testParameterizedConstructor() {
        User user = new User("testUser", "testPassword");
        
        assertNull(user.getUserId(), "User ID should be null for this constructor");
        assertEquals("testUser", user.getUsername(), "Username should be set correctly");
        assertEquals("testPassword", user.getPassword(), "Password should be set correctly");
        assertEquals(User.Role.EMPLOYEE, user.getRole(), "Default role should be EMPLOYEE");
    }

    @Test
    void testFullConstructor() {
        User user = new User(1, "testUser", "testPassword", User.Role.EMPLOYEE);

        // Assert
        assertEquals(1, user.getUserId(), "User ID should be set correctly");
        assertEquals("testUser", user.getUsername(), "Username should be set correctly");
        assertEquals("testPassword", user.getPassword(), "Password should be set correctly");
        assertEquals(User.Role.EMPLOYEE, user.getRole(), "Default role should be EMPLOYEE");
    }

    @Test
    void shouldSetandGetCorrectFields() {
        User user = new User();
        user.setUserId(1);
        user.setUsername("testUser");
        user.setPassword("testPassword");
        user.setRole(User.Role.EMPLOYEE);

        // Assert
        assertEquals(1, user.getUserId(), "User ID should be set correctly");
        assertEquals("testUser", user.getUsername(), "Username should be set correctly");
        assertEquals("testPassword", user.getPassword(), "Password should be set correctly");
        assertEquals(User.Role.EMPLOYEE, user.getRole(), "Default role should be EMPLOYEE");
    }

    @Test
    void testToString() {
        User user = new User(1, "testUser", "testPassword", User.Role.EMPLOYEE);
        assertEquals("User{userId=1, username='testUser', password='testPassword', role=EMPLOYEE}", user.toString());
    }

    @Test
    void equalsTest() {
        User user1 = new User(1, "testUser", "testPassword", User.Role.EMPLOYEE);
        User user2 = new User(1, "testUser", "testPassword", User.Role.EMPLOYEE);
        assertEquals(user1, user2);
    }
    
}
