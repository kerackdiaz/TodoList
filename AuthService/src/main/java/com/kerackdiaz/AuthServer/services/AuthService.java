package com.kerackdiaz.AuthServer.services;

import com.kerackdiaz.AuthServer.dtos.UserDTO;
import com.kerackdiaz.AuthServer.dtos.singIn;
import com.kerackdiaz.AuthServer.models.AuthResponse;
import com.kerackdiaz.AuthServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public AuthResponse register(singIn request) throws Exception {
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        ResponseEntity<UserDTO> response = restTemplate.postForEntity("http://localhost:8081/users/register", request, UserDTO.class);
        UserDTO registeredUser = response.getBody();
        String accessToken;
        if (registeredUser != null) {
            accessToken = jwtUtil.generateToken(String.valueOf(registeredUser.getId()), "ROLE_USER", registeredUser.getEmail());
        } else {
            // Manejar el caso de error cuando registeredUser es null
            throw new Exception("Failed to register user");
        }
        return new AuthResponse(accessToken, registeredUser);
    }
}