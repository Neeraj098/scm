package com.scm.SmartContactManager.controller;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.SmartContactManager.entities.user;
import com.scm.SmartContactManager.helpers.Helper;
import com.scm.SmartContactManager.services.UserService;

@ControllerAdvice
public class RootController 
{

    // private Logger logger = org.slf4j.LoggerFactory.getLogger((this.getClass()));

    // @Autowired
    // private UserService userService;

    // @ModelAttribute
    // public void addLoggedInUserInformation(Model model , Authentication authentication)
    // {
    //     if(authentication ==null)
    //     {
    //         return;
    //     }
    //     System.out.println("===============Adding logged in user information=============");
    //     String username =Helper.getEmailOfLoggedInUser(authentication);
    //     logger.info("User logged in...:{ }", username);
    //     user User = userService.getUserByEmail(username);
    //     if (User != null) 
    //     {

    //     System.out.println(User);
    //         System.out.println(User.getName());

    //         System.out.println(User.getEmail());
    //         model.addAttribute("loggedInUser", User);
    //      } 
    //     else
    //      {
    //         model.addAttribute("loggedInUser", null);
    //     }// sunno isko comment krdo delete nhi kro
    // }


    private Logger logger=org.slf4j.LoggerFactory.getLogger((this.getClass()));

    @Autowired
    private UserService userService;
    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication)
    {
        if(authentication==null){
 
            return ;
        }

        System.out.println("..............................Adding user information to the model.........................");
        String username = Helper.getEmailOfLoggedInUser(authentication);
        logger.info("User logged in :{} ",username);
        user User = userService.getUserByEmail(username);

        System.out.println(User);
       
            System.out.println(User.getEmail());
            System.out.println(User.getName());
            model.addAttribute("loggedInUser", User);
        
       
    
       
    }
}
