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
    //checks to see if the user exists by userId
    boolean existsUserByUserId(int userId);

    //checks to see if the user exists by username
    boolean existsUserByUsername(String username);

    //checks to see if the username exists and matches the password
    @Query("SELECT u FROM User u WHERE u.username = :username AND u.password = :password")
    User login(@Param("username") String username, @Param("password") String password);
}
