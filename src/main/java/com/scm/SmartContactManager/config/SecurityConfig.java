package com.scm.SmartContactManager.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.scm.SmartContactManager.impl.SecurityCustomerUserDetailService;

@Configuration
public class SecurityConfig 
{
    //user create and login using java code with in the memory
    
//  private InMemoryUserDetailsManager  inMemoryUserDetailsManager;

//     @Bean
//     public UserDetailsService userDetailsService()
//     {
//         UserDetails user1 = User
//         .withDefaultPasswordEncoder()
//         .username("admin123")
//         .password("admin123")
//         .roles("ADMIN","USER")
//         .build();


//         UserDetails user2 = User 
//         .withUsername("user123")
//         .password("password")
//       //  .roles(null)
//         .build();
//         var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1 , user2);
//         return inMemoryUserDetailsManager;
//     }

@Autowired
private SecurityCustomerUserDetailService userDetailService;

@Autowired
private OAuthAuthenticationSuccessHandler handler;

//configuration of authentication provider

@Bean
public AuthenticationProvider authenticationProvider()
{
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    //user detail service ka object
    daoAuthenticationProvider.setUserDetailsService( userDetailService);
    //password encoder ka object
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
}

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
{

    //configurations

    httpSecurity.authorizeHttpRequests(authorize->{
        // authorize.requestMatchers("/home","/register","/login").permitAll();
        authorize.requestMatchers("/user/**").authenticated();
        authorize.anyRequest().permitAll();
    });

    //form default login
    //agar hume kuch bhi chnge krna hua toh hum yh aaenge
    httpSecurity.formLogin(formLogin->
    {
        formLogin.loginPage("/login");
        formLogin.loginProcessingUrl("/authenticate");
        formLogin.successForwardUrl("/user/profile");
     // formLogin.failureForwardUrl("/login?error=true");
        formLogin.usernameParameter("email");
        formLogin.passwordParameter("password");
        // formLogin.failureHandler(new AuthenticationFailureHandler() 
        // {

        //     @Override
        //     public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,AuthenticationException exception) throws IOException, ServletException {
            
                
        //     }
            
            
        // });

        // formLogin.successHandler(new AuthenticationSuccessHandler() {

        //     @Override
        //     public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        //             Authentication authentication) throws IOException, ServletException {
        //         // TODO Auto-generated method stub
        //         throw new UnsupportedOperationException("Unimplemented method 'onAuthenticationSuccess'");
        //     }
            
        // });
    });

    httpSecurity.csrf(AbstractHttpConfigurer::disable);
    httpSecurity.logout(logoutForm->
    {
        logoutForm.logoutUrl("/do-logout");
        logoutForm.logoutSuccessUrl("/login?logout=true");
    });

    //oauth config

    httpSecurity.oauth2Login(oauth->{
        oauth.loginPage("/login");
        oauth.successHandler(handler);
    });
   return httpSecurity.build();
}

@Bean
public PasswordEncoder passwordEncoder()
{
    return new BCryptPasswordEncoder();
}


}
