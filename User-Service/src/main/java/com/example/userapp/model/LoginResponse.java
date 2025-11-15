package com.example.userapp.model;

public class LoginResponse {

    private String id; // Change from Long to String
    private String fullName;
    private String email;
    private String phoneNumber;
    private String token;

    public LoginResponse(String id, String fullName, String email, String phoneNumber, String token) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getToken() {
        return token;
    }
}
