package com.bankingapplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bankingapplication.model.Users;
import com.bankingapplication.repository.UserRepository;
import com.bankingapplication.util.JwtUtils;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public String registerUser(Users user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "User registered successfully";
    }

    public String loginUser(Users user) {
        Users existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null && (passwordEncoder.matches(user.getPassword(), existingUser.getPassword()) || user.getPassword().equals(existingUser.getPassword()))) {
            String token = jwtUtils.generateToken(existingUser);
            return "User logged in successfully. Token: " + token;
        } else {
            return "Invalid username or password";
        }
    }

    public String forgotPassword(String email) {
        Users user = userRepository.findByEmail(email);
        if (user != null) {
            // Logic to send password reset link to user's email
            return "Password reset link sent to user's email";
        } else {
            return "User not found with the provided email";
        }
    }

    public void resetPassword(Users user) {
        Users existingUser = userRepository.findById(user.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(existingUser);
    }
}