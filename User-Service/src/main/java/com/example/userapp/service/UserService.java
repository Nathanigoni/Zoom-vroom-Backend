package com.example.userapp.service;

import com.example.userapp.model.LoginResponse;
import com.example.userapp.model.UserClass;
import com.example.userapp.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("A user with this email already exists.");
        }

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

    public void deleteById(String id) {
        repo.deleteById(id);
    }

    public UserClass updateuser(String id, UserClass userData) {
        UserClass existingUser = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingUser.setFullName(userData.getFullName());
        existingUser.setEmail(userData.getEmail());
        existingUser.setPhoneNumber(userData.getPhoneNumber());

        if (userData.getPassword() != null && !userData.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        }

        return repo.save(existingUser);
    }


    public UserClass getUserById(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
