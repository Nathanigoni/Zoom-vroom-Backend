package com.example.userapp.controller;

import com.example.userapp.model.UserClass;
import com.example.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.servlet.function.ServerResponse.status;

@RestController
@RequestMapping("/User")
public class UserController {
    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserClass> createuser(@RequestBody UserClass user){
        try {
            UserClass userClass = service.register(user);
            return new ResponseEntity<>(userClass, HttpStatus.CREATED);

        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserClass user) {
        String token = service.verify(user);
        return ResponseEntity.ok(token);
    }

}
