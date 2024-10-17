package com.bankingapplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankingapplication.model.Users;
import com.bankingapplication.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public String registerUser(@RequestBody Users user) {
        authService.registerUser(user);
        return "User registered successfully";
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody Users user) {
        return authService.loginUser(user);
    }

    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestBody String email) {
        authService.forgotPassword(email);
        return "Password reset link sent to user's email";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestBody Users user) {
        authService.resetPassword(user);
        return "Password reset successfully";
    }
}