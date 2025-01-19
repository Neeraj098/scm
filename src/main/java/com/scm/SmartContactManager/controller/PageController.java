package com.scm.SmartContactManager.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.scm.SmartContactManager.entities.user;
import com.scm.SmartContactManager.forms.UserForm;
import com.scm.SmartContactManager.helpers.Message;
import com.scm.SmartContactManager.helpers.MessageType;
import com.scm.SmartContactManager.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class PageController 
{


    @Autowired
    private UserService userService;
    //home page

@GetMapping("/")
    public String index()
    {
       return "redirect:/home";
    }
    @RequestMapping("/home")
    public String home(Model model)
    {
       System.out.println("..........////////////>>>>>>>>>>>>>>>>");
       model.addAttribute("name","Substring Technologies");
       model.addAttribute("neeraj","hello friends this side me");
       return "home";
    }

    //about page

    @RequestMapping("/about")
    public String aboutpage()
    {
        System.out.println("About page loading.................");
        return "about";
    }
     
    // services page

    @RequestMapping("/services")
    public String servicespage()
    {
        System.out.println("Services page loading.................");
        return "services";
    }

    //contact page

    @GetMapping("/contact")
    public String contact()
    {
        return new String("contact");
    }

    //login page

    @GetMapping("/login")
    public String login()
    {
        return new String("login");
    }


    //signup/register page

    @GetMapping("/register")
    public String register(Model model)
    {
        UserForm userForm = new UserForm();
        //default data bhi daal skte h
       // userForm.setName("halla singh");
        model.addAttribute("userForm", userForm);
        return new String("register");
    }


    @RequestMapping(value="/do-register", method=RequestMethod.POST)
    
    public String processRegister( @Valid @ModelAttribute UserForm userForm ,BindingResult rBindingResult, HttpSession session)
    {
        System.out.println("Processing registration");
        //fetching
        //userform
        System.out.println(userForm);

        //vaidating

        if(rBindingResult.hasErrors())
        {
            return "register";
        }
        
        //save to database

        //userservice

// =======================================================
        // user User =user.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("C:/Users/User/Downloads/images.png")
        // .build();
//============================================================

       user User = new user();
       User.setName(userForm.getName());
         User.setEmail(userForm.getEmail());
         User.setPassword(userForm.getPassword());
         User.setAbout(userForm.getAbout());
         User.setPhoneNumber(userForm.getPhoneNumber());
        // User.setEnabled(false);
         User.setProfilePic("C:/Users/User/Downloads/images.png");


        user  savedUser = userService.saveUser(User);
        System.out.println("user saved................");
        System.out.println(savedUser);

        // User user = new User();
        // user.setName(userForm.getName());
        // user.setEmail(userForm.getEmail());
        // user.setPassword(userForm.getPassword());
        // user.setAbout(userForm.getAbout());
        // user.setPhoneNumber(userForm.getPhoneNumber());
        // user.setEnabled(false);


        Message message = Message.builder().content("Registration Successfull").type(MessageType.blue).build();

        //saving
        session.setAttribute("message", message);
        //redirecting
        return "redirect:/register";
    }
}
