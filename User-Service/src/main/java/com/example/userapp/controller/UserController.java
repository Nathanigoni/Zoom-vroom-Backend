package com.example.userapp.controller;

import com.example.userapp.model.LoginResponse;
import com.example.userapp.model.UserClass;
import com.example.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import org.springframework.security.core.AuthenticationException;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserClass user) {
        try {
            UserClass registeredUser = service.register(user);
            registeredUser.setPassword(null);
            return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An unexpected error occurred.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserClass user) {
        try {
            LoginResponse response = service.verify(user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred during login", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAdmin(@PathVariable Long id, @RequestBody UserClass user) {
        try {
            UserClass updated = service.updateuser(id, user);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteAdmin(@PathVariable int id){
        service.deleteById(id);
    }

}
