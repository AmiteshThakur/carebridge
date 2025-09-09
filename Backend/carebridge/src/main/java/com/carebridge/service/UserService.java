package com.carebridge.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.carebridge.dto.UserRegistrationDto;
import com.carebridge.model.User;




public interface  UserService {

   User registerUser(UserRegistrationDto userRegistrationDto);
    Optional<User> getUserByEmail(String email);
}
