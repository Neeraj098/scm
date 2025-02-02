package com.scm.SmartContactManager.forms;



import org.springframework.web.multipart.MultipartFile;

import com.scm.SmartContactManager.entities.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContactForm 
{
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String description;
    private boolean favorite=false;
    private String facebookLink;
    private String linkedInLink;


    private MultipartFile profileimage;


}
