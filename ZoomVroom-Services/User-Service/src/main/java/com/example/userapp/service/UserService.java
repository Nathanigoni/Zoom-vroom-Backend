package com.example.userapp.service;

import com.example.userapp.model.UserClass;
import com.example.userapp.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private Repo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JWTUtil jwtService;


    public UserClass register(UserClass user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("user was registered successfully");
        return repo.save(user);
    }

    public String verify(UserClass user) {

        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                user.getPassword()));
        if (authentication.isAuthenticated())
            //return "success";
            return jwtService.generateToken(user.getEmail());

        return "failure";
    }
}
