package com.carebridge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carebridge.dto.LoginRequestDto;
import com.carebridge.dto.UserRegistrationDto;
import com.carebridge.model.User;
import com.carebridge.security.JwtTokenProvider;
import com.carebridge.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;  // <--- This is the Auth Manager

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    /**
     * Register a new user
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegistrationDto userDto) {
        User savedUser = userService.registerUser(userDto);
        return ResponseEntity.ok("User registered successfully with email: " + savedUser.getEmail());
    }

    /**
     * Authenticate user and return JWT token
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        // Authenticate user
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(authentication.getName());

        // Return response
        return ResponseEntity.ok(new JwtAuthResponse(token));
    }

    /**
     * DTO for sending token response
     */
    private record JwtAuthResponse(String token, String type) {
        public JwtAuthResponse(String token) {
            this(token, "Bearer");
        }
    }
}
