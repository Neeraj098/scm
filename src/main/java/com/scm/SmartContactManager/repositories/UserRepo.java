package com.scm.SmartContactManager.repositories;

import com.scm.SmartContactManager.entities.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<user , String>
{
    //extra methods db related
    //custom query mehtods
    //custom finder methods

    // Optional<user> findByEmail(String email);

    // Optional<user> findByEmailAndPassword(String email, String password);

    Optional<user> findByEmail(String email);

    Optional<user> findByEmailAndPassword(String email, String password);

    
} 