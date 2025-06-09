package com.example.userapp.repository;

import com.example.userapp.model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repo extends JpaRepository<UserClass, Integer> {
    UserClass findByEmail(String email);

}
