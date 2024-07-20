package com.kerackdiaz.AuthServer.services;

import com.kerackdiaz.AuthServer.dtos.LoginDTO;
import com.kerackdiaz.AuthServer.dtos.UserDTO;
import com.kerackdiaz.AuthServer.dtos.singIn;
import com.kerackdiaz.AuthServer.models.AuthResponse;
import com.kerackdiaz.AuthServer.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
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

    public ResponseEntity<?> register(singIn request) {
        try {
            request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
            ResponseEntity<UserDTO> response = restTemplate.postForEntity("http://localhost:8081/users/register", request, UserDTO.class);
            UserDTO registeredUser = response.getBody();
            return ResponseEntity.ok("User successfully registered");
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getResponseBodyAsString());
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    public AuthResponse login(LoginDTO request) throws Exception {
        ResponseEntity<UserDTO> response = restTemplate.postForEntity("http://localhost:8081/users/login", request, UserDTO.class);
        UserDTO user = response.getBody();
        String accessToken;
        if (user != null) {
            accessToken = jwtUtil.generateToken(String.valueOf(user.getId()), user.getRole(), user.getEmail());
        } else {
            throw new Exception("Failed to login user");
        }
        return new AuthResponse(accessToken);
    }
}