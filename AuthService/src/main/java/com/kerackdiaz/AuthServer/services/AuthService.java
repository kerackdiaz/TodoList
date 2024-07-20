package com.kerackdiaz.AuthServer.services;

import com.kerackdiaz.AuthServer.dtos.singIn;
import com.kerackdiaz.AuthServer.models.AuthResponse;
import com.kerackdiaz.AuthServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private final RestTemplate restTemplate;

    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(RestTemplate restTemplate, JwtUtil jwtUtil) {
        this.restTemplate = restTemplate;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse register(singIn request) {
        //do validation if a user exist in DB

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        User registeredUser = restTemplate.postForObject("http://localhost:8081/users/register", request, User.class);
        assert registeredUser != null;
        String accessToken = jwtUtil.generateToken(registeredUser.getId(), registeredUser.getEmail(), registeredUser.getRole());

        return new AuthResponse(accessToken);
    }
}