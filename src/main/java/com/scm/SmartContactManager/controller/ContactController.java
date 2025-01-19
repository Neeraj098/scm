package com.scm.SmartContactManager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scm.SmartContactManager.forms.ContactForm;

@Controller
@RequestMapping("/user/contact")
public class ContactController {

    @RequestMapping("/add")
    public String addContactView(Model model){
        ContactForm contactForm=new ContactForm();
        // contactForm.setName("Gattu singh");
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }
}
