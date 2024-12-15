package com.fullstackpep.ticketing_project.service;

import com.fullstackpep.ticketing_project.entity.User;
import com.fullstackpep.ticketing_project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

//what logic should be in this service class?
//register a user
//login a user

@Service
public class UserService {
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


}
