package com.scm.SmartContactManager.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.SmartContactManager.entities.user;
import com.scm.SmartContactManager.helpers.AppConstants;
import com.scm.SmartContactManager.helpers.ResourceNotFoundException;
import com.scm.SmartContactManager.services.UserService;
import com.scm.SmartContactManager.repositories.UserRepo;

//import jakarta.persistence.Access;

@Service
public class UserServiceImpl implements UserService
{

    
    // private UserRepo userRepo;
    // @Override
    // public user saveUser(user User) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'saveUser'");
    // }

    // @Override
    // public Optional<user> getUserById(String id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getUserById'");
    // }

    // @Override
    // public Optional<user> updateUser(user User) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    // }

    // @Override
    // public void deleteUser(String id) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
    // }

    // @Override
    // public boolean isUserExist(String userId) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isUserExist'");
    // }

    // @Override
    // public boolean isUserExistByEmail(String email) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'isUserExistByEmail'");
    // }

    // @Override
    // public List<user> getAllUsers() {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'getAllUsers'");
    // }

    //=======================changed======================================================================================
    

     @Autowired
    
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public user saveUser(user User)
     {
        String userId = UUID.randomUUID().toString();
        User.setUserId(userId);
        //password encode
        //user.setpassword(userId)
        User.setPassword(passwordEncoder.encode(User.getPassword()));

        
        User.setRoleList(List.of(AppConstants.ROLE_USER));
        logger.info(User.getProvider().toString());
        
        return userRepo.save(User);
    }

    @Override
    public Optional<user> getUserById(String id) {
        
        return userRepo.findById(id);
    }

    @Override
    public Optional<user> updateUser(user User) {
      
        user User2 =userRepo.findById(User.getUserId()).orElseThrow(() -> new ResourceNotFoundException("User not found"));

        //update user2 from user

        User2.setName(User.getName());
        User2.setEmail(User.getEmail());
        User2.setPassword(User.getPassword());
        User2.setAbout(User.getAbout());
        User2.setPhoneNumber(User.getPhoneNumber());
        User2.setProfilePic(User.getProfilePic());
        User2.setEnabled(User.isEnabled());
        User2.setEmailVerified(User.isEmailVerified());
        User2.setPhoneVerified(User.isPhoneVerified());
        User2.setProvider(User.getProvider());
        User2.setProviderUserId(User.getProviderUserId());

        // save the user in the database

        user save = userRepo.save(User2);

        return Optional.ofNullable(save);
    }

    @Override
    public void deleteUser(String id)
     {
        user User2 =userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
         userRepo.delete(User2);
    }

    @Override
    public boolean isUserExist(String userId) 
    {
        user User2 =userRepo.findById(userId).orElse(null);
        return User2!=null ? true : false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        user User = userRepo.findByEmail(email).orElse(null);
        return User != null ? true : false;
    }

    @Override
    public List<user> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public user getUserByEmail(String email) {
    
        //return userRepo.findByEmail(email).orElseThrow()-> new ResourceNotFoundException("User not found");

       // return userRepo.findByEmail(email).orElseThrow() ->  new ResourceNotFoundException("user not found");
       return userRepo.findByEmail(email).orElse(null);
    }


}
