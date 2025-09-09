package com.carebridge.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.carebridge.model.User;
import com.carebridge.service.UserService;

public class CustomUserDetailsService implements  UserDetailsService{
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        User user= userService.getUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found with email "+ email));

            return UserPrincipal.create(user);
    }

    
    

}
