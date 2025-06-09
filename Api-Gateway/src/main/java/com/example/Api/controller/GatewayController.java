package com.example.Api.controller;


import com.example.Api.service.ApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class GatewayController {
    private final ApiService service;

    public GatewayController(ApiService service) {
        this.service = service;
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserClass user) {
//        return service.login(user);
//    }
}
