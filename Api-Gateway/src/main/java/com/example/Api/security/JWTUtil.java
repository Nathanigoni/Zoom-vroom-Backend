package com.example.Api.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;


@Service
public class JWTUtil {
    public static final String  SECRET_KEY = "your_very_secure_and_long_secret_key_of_at_least_32_characters";

    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));
    }

    // Extract all claims from the JWT token
    private Claims extractAllClaims(String token) {
        return Jwts.parser()  // Use parserBuilder
                .setSigningKey(getKey())  // Set the signing key
                .build()
                .parseClaimsJws(token)  // Parse the token
                .getBody();  // Return claims
    }

    // Extract specific claim (e.g., username) from the JWT token
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);  // Apply the claim extractor function
    }

    // Extract the username (subject) from the JWT token
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);  // Extract the username (subject)
    }

    // Validate the JWT token
    public boolean validateToken(String token) {
        try {
            Claims claims = extractAllClaims(token);  // Extract claims from the token
            return !claims.getExpiration().before(new Date());  // Check if token has expired
        } catch (Exception e) {
            return false;  // Return false if the token is invalid or an error occurs
        }
    }
}

