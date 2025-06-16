package com.example.userapp.repository;

import com.example.userapp.model.UserClass;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Repo extends JpaRepository<UserClass, Long> {
    Optional<UserClass> findByEmail(String email);
    Optional<UserClass> findByPhoneNumber(String email);


}
