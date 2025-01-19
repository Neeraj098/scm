package com.scm.SmartContactManager.services;

import java.util.List;
import java.util.Optional;

import com.scm.SmartContactManager.entities.user;

public interface UserService 
{
    user saveUser (user User);

    Optional<user> getUserById (String id);

    Optional<user> updateUser (user User);

    void deleteUser (String id);

    boolean isUserExist (String userId);

    boolean isUserExistByEmail(String email);

    List<user> getAllUsers();

    user getUserByEmail(String email);


    //add more methods here related user service logic
}
