package com.scm.SmartContactManager.forms;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserForm 
{
    @NotBlank(message = "Username is Required")
    @Size(min = 5 ,message = "Minimum 5 Characters are Required")
    private String name;

    @Email(message = "Invalid Email Address")
    @NotBlank( message = "Email is required")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min=6 , message = "Minimum 6 Characters are Required")
    private String password;

@NotBlank(message = "About is Mandatory")
    private String about;

    @Size(min = 8 , max = 12 , message = "Invalid Number")
    private String phoneNumber;


}
