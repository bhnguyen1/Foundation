package com.fullstackpep.ticketing_project.repository;

import com.fullstackpep.ticketing_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/*
 * Point of this repository interface is to provide a way to interact with the database
 * to perform CRUD operations on the User entity
 * Table name: user
 * userId (primary key) : type int
 * username (unique) : type varchar (255)
 * password : type varchar (255)
 * role : type varchar (255)
 * 
 * two things to check for:
 * check if account already exists
 * ------------------------------------------------------------
 * login into the account
 */
public interface UserRepository extends JpaRepository<User, Integer> {
    
    
}
