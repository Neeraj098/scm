package com.scm.SmartContactManager.entities;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

@Entity(name = "user")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class user implements UserDetails
{


    @Id
    private String userId;
    @Column(name = "user_name", nullable = false)

    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    @Getter(AccessLevel.NONE)
    private String password;
    @Column(length = 1000)
    private String about;
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;

   
    @Getter(AccessLevel.NONE)
    // information
    private boolean enabled = true;

    private boolean emailVerified = false;
    private boolean phoneVerified = false;

    @Enumerated(value = EnumType.STRING)
    // SELF, GOOGLE, FACEBOOK, TWITTER, LINKEDIN, GITHUB
    private Providers provider = Providers.SELF;
    private String providerUserId;

    // // add more fields if needed
     @OneToMany(mappedBy = "User", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    //@OneToMany(mappedBy = "User")
     private List<Contact> contacts = new ArrayList<>();


     @ElementCollection(fetch = FetchType.EAGER)
     private List<String> roleList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //list of roles [user,admin]
        //collection of simplegrantedauthority(roles[admin,user])
        Collection<SimpleGrantedAuthority> roles = roleList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
        return roles;
    }

    // email id hi yha pr username h
    @Override
    public String getUsername() 
    {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;

    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;

    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;

    }

    @Override
    public boolean isEnabled()
    {
        return this.enabled;

    }

    @Override
    public String getPassword() 
    {
       return this.password;
    }




}
