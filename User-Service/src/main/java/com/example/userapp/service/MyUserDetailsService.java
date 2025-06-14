package com.example.userapp.service;

import com.example.userapp.model.UserClass;
import com.example.userapp.model.UserPrincipal;
import com.example.userapp.repository.Repo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private Repo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserClass> userClass = repo.findByEmail(username);

        if (userClass.isEmpty()) {
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }

        return new UserPrincipal(userClass.get());
    }
}
