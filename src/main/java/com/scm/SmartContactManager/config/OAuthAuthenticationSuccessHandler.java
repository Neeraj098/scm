package com.scm.SmartContactManager.config;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.SmartContactManager.entities.Providers;
import com.scm.SmartContactManager.entities.user;
import com.scm.SmartContactManager.helpers.AppConstants;
import com.scm.SmartContactManager.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthenticationSuccessHandler implements AuthenticationSuccessHandler
{
    Logger logger = LoggerFactory.getLogger(OAuthAuthenticationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @SuppressWarnings("null")
    public void onAuthenticationSuccess(
    
        HttpServletRequest request,
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException

        {
            logger.info("OAuthAuthenticationSuccessHnadler");

            var oauth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;

            String authorizedClientRegistrationId = oauth2AuthenticationToken.getAuthorizedClientRegistrationId();

            logger.info(authorizedClientRegistrationId);
            var oauthUser = (DefaultOAuth2User) authentication.getPrincipal();

            oauthUser.getAttributes().forEach((key,value) ->
            {
               logger.info(key + " : " + value);
           });

            user User = new user();
            User.setUserId(UUID.randomUUID().toString());
        User.setRoleList(List.of(AppConstants.ROLE_USER));
        User.setEmailVerified(true);
        User.setEnabled(true);
        User.setPassword("dummy");


        if(authorizedClientRegistrationId.equalsIgnoreCase("google"))
        {
            User.setEmail(oauthUser.getAttribute("email").toString());
            User.setProfilePic(oauthUser.getAttribute("picture").toString());
            User.setName(oauthUser.getAttribute("name").toString());
            User.setProviderUserId(oauthUser.getName());
            User.setProvider(Providers.GOOGLE);
            User.setAbout("This account is created using google.");
        }

        else if(authorizedClientRegistrationId.equalsIgnoreCase("github"))
        {
            String email = oauthUser.getAttribute("email") != null ? oauthUser.getAttribute("email").toString(): oauthUser.getAttribute("login").toString() + "@gmail.com";
            String picture = oauthUser.getAttribute("avatar_url").toString();
            String name = oauthUser.getAttribute("login").toString();
            String providerUserId = oauthUser.getName();

    User.setEmail(email);
    User.setProfilePic(picture);
    User.setName(name);
    User.setProviderUserId(providerUserId);
    User.setProvider(Providers.GITHUB);

    User.setAbout("This account is created using github");
        }

        else if(authorizedClientRegistrationId.equalsIgnoreCase("linkedIn"))
        {

        }
        else
        {
            logger.info("OAuthAuthenticationSuccessHandler : Unknown provider");
        }

        user user1 = userRepo.findByEmail(User.getEmail()).orElse(null);

        if(user1 == null)
        {
            userRepo.save(User);
            System.out.println("User Saved :" +User.getEmail());
        }

       

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
        

        }

 }
  


