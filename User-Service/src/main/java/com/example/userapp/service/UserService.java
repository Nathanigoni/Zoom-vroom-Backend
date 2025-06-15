package com.example.userapp.service;

import com.example.userapp.model.LoginResponse;
import com.example.userapp.model.UserClass;
import com.example.userapp.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        // Check if email already exists
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }
        // Check if PhoneNumber already exists
        if (repo.findByPhoneNumber(user.getPhoneNumber()).isPresent()) {
            throw new IllegalArgumentException("A user with this phone number already exists.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repo.save(user);
    }
    public LoginResponse verify(UserClass user) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(user.getEmail());

            UserClass fullUser = repo.findByEmail(user.getEmail())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            return new LoginResponse(
                    fullUser.getId(),
                    fullUser.getFullName(),
                    fullUser.getEmail(),
                    fullUser.getPhoneNumber(),
                    token
            );
        }

        throw new BadCredentialsException("Invalid email or password");
    }
}
