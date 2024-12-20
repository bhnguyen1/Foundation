package com.fullstackpep.ticketing_project.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fullstackpep.ticketing_project.repository.UserRepository;
import com.fullstackpep.ticketing_project.entity.User;


@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void shouldThrowExceptionWhenUsernameExists() {
        User user = new User("existingUser", "password");
        when(userRepository.existsUserByUsername("existingUser")).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> {
            userService.addUser(user);
        });
        
        verify(userRepository, times(1)).existsUserByUsername("existingUser");
        verify(userRepository, never()).save(any());
    }

    @Test
    public void shouldThrowExceptionWhenUsernameIsEmpty() {
        User user = new User("", "password");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.addUser(user);
        });
        
        // verify(userRepository, never()).existsUserByUsername("");
        verify(userRepository, never()).save(any());
    }
    
    @Test
    public void shouldThrowExceptionWhenPasswordIsEmpty() {
        User user = new User("username", "");

        assertThrows(IllegalArgumentException.class, () -> {
            userService.addUser(user);
        });
        
        verify(userRepository, never()).save(any());
    }

    @Test
    public void shouldSetRoleToEmployeeWhenRoleIsNull() {
        User user = new User("username", "password");
        when(userRepository.existsUserByUsername("username")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(user);

        assertEquals(User.Role.EMPLOYEE, savedUser.getRole());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void shouldSaveUserWhenValid() {
        User user = new User("username", "password");
        when(userRepository.existsUserByUsername("username")).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        User savedUser = userService.addUser(user);

        assertEquals(user, savedUser);
        verify(userRepository, times(1)).save(user);
    }

    @Test 
    public void shouldThrowExceptionWhenInvalidLogin() {
        when(userRepository.login("invalidUsername", "invalidPassword")).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> {
            userService.login("invalidUsername", "invalidPassword");
        });

        verify(userRepository, times(1)).login("invalidUsername", "invalidPassword");
    }

    @Test
    public void shouldReturnUserWhenValidLogin() {
        User user = new User("validUsername", "validPassword");
        when(userRepository.login("validUsername", "validPassword")).thenReturn(user);

        User loggedInUser = userService.login("validUsername", "validPassword");

        assertEquals(user, loggedInUser);
        verify(userRepository, times(1)).login("validUsername", "validPassword");
    }

}
