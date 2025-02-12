package com.kerackdiaz.AuthServer.controllers;


import com.kerackdiaz.AuthServer.dtos.LoginDTO;
import com.kerackdiaz.AuthServer.dtos.singIn;
import com.kerackdiaz.AuthServer.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody singIn request) throws Exception {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }

}
