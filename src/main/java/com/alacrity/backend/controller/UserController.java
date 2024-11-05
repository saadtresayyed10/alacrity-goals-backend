package com.alacrity.backend.controller;

import com.alacrity.backend.model.User;
import com.alacrity.backend.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody User user) {
        if(!user.getPassword().equals(user.getConfirmPassword())) {
            return ResponseEntity.badRequest().body("Password do not match with Confirm Password.");
        }

        user.setConfirmPassword(null);

        User savedUser = userRepo.save(user);
        return ResponseEntity.ok("User signed up with email: " + savedUser.getEmail());
    }

}
