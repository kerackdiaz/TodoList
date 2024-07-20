package com.kerackdiaz.AuthServer.controllers;

import com.kerackdiaz.AuthServer.dtos.singIn;
import com.kerackdiaz.AuthServer.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody singIn request) {
        return ResponseEntity.ok(authService.register(request));
    }

}
