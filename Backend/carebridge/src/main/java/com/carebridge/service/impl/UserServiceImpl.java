package com.carebridge.service.impl;

import java.util.Optional;

import com.carebridge.dto.UserRegistrationDto;
import com.carebridge.model.User;
import com.carebridge.repository.UserRepository;
import com.carebridge.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User registerUser(
    UserRegistrationDto userRegistrationDto
  ) {
    if(userRepository.findByEmail(userRegistrationDto.getEmail()).isPresent()){
        throw new RuntimeException("Email already in use: "+ userRegistrationDto.getEmail());
    }

    User user= User.builder()
                .name(userRegistrationDto.getName())
                .email(userRegistrationDto.getEmail())
                .password(passwordEncoder.encode(userRegistrationDto.getPassword()))
                .role(userRegistrationDto.getRole()).build();

                return userRepository.save(user);
  }

  @Override
  public Optional<User> getUserByEmail(String email) {
    return userRepository.findByEmail(email);
  }
}
