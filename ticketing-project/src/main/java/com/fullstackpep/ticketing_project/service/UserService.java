package com.fullstackpep.ticketing_project.service;

import com.fullstackpep.ticketing_project.entity.User;
import com.fullstackpep.ticketing_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/*what logic should be in this service class?
//register a user
//cases: 
//check if account already exists
//can't be blank username 
//can't be blank password
//default role is employee
------------------------------------------------------------
// login a user
// 
*/

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(User user) {
        if(userRepository.existsUserByUsername(user.getUsername())) {
            throw new IllegalStateException("Username already exists!");
        }
        if(user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new IllegalArgumentException("Username or password can't be blank!");
        }
        user.setRole(User.Role.EMPLOYEE);
        
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.login(username, password);
        if(user == null) {
            throw new IllegalArgumentException("Invalid username or password!");
        }
        return user;
    }

}
