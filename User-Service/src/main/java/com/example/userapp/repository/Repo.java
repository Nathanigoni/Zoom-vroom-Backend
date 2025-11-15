package com.example.userapp.repository;

import com.example.userapp.model.UserClass;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface Repo extends MongoRepository<UserClass, String> {
    Optional<UserClass> findByEmail(String email);
    Optional<UserClass> findByPhoneNumber(String phoneNumber);
}

