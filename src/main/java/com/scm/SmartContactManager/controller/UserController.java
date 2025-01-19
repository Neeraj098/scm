package com.scm.SmartContactManager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.SmartContactManager.entities.user;
import com.scm.SmartContactManager.helpers.Helper;
import com.scm.SmartContactManager.services.UserService;


@Controller
@RequestMapping("/user")
public class UserController 
{
    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    
       


    //user dashborard page
   

    @RequestMapping("/dashboard")
    public String userDashboard() {
        System.out.println("User Dashboard");
        return "user/dashboard";
    }

    // user profile page

    @RequestMapping(value = "/profile")
  public String userProfile( Model model, Authentication authentication)
  {
    //String name=principal.getName();
    // String username = Helper.getEmailOfLoggedInUser(authentication);

    // logger.info("User logged in :{} ",username);


    // user User = userService.getUserByEmail(username);



    // System.out.println(User.getEmail());
    // System.out.println(User.getName());

    // model.addAttribute("loggedInUser", User);

    return "user/profile";
  }

    //user add contacts page

    //user view page


    //user edit page

    //user delete page



}
