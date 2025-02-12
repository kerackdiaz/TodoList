package com.kerackdiaz.Gateway_server.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtUtils {
//    @Value("${jwt.secret:}")
    private String secret;

    private static SecretKey SECRET_KEY;

    @PostConstruct
    public void init() {

        String envSecret = System.getenv("jwt.secret");
        if (envSecret != null && !envSecret.isEmpty()) {
            secret = envSecret;
        }

        if (secret == null || secret.isEmpty()) {
            throw new IllegalStateException("jwt.secret is missing in both environment variables and application.properties");
        }

        SECRET_KEY = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}