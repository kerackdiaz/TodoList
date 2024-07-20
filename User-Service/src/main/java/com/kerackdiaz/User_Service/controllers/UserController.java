package com.kerackdiaz.User_Service.controllers;


import com.kerackdiaz.User_Service.dtos.RegisterRecord;
import com.kerackdiaz.User_Service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRecord registerRecord) {
        return ResponseEntity.ok(userService.Register(registerRecord));
    }
}
