package com.bankingapplication.controller;

import com.bankingapplication.model.Users;
import com.bankingapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Users> getUser(@PathVariable Long userId) {
        Users user = userService.getUserById(userId);
        return ResponseEntity.ok(user);
    }

    // Add other user-related APIs here

}