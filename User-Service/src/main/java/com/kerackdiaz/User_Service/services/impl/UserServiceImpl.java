package com.kerackdiaz.User_Service.services.impl;

import com.kerackdiaz.User_Service.dtos.RegisterRecord;
import com.kerackdiaz.User_Service.dtos.UserDTO;
import com.kerackdiaz.User_Service.models.User;
import com.kerackdiaz.User_Service.repositories.UserRepository;
import com.kerackdiaz.User_Service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    public Map<String,Object> Register(RegisterRecord registerRecord){
        Map<String, Object> response = new HashMap<>();
        try {
            if (registerRecord.firstName().isBlank()) {
                response.put("error", ResponseEntity.badRequest());
                response.put("message", "the first name are empty");
                return response;
            }
            if (registerRecord.lastName().isBlank()) {
                response.put("error", ResponseEntity.badRequest());
                response.put("message", "the last name are empty");
                return response;
            }
            if (registerRecord.email().isBlank()) {
                response.put("error", ResponseEntity.badRequest());
                response.put("message", "the email are empty");
                return response;
            }
            if (registerRecord.password().isBlank()) {
                response.put("error", ResponseEntity.badRequest());
                response.put("message", "the password are empty");
                return response;
            }
            if (UserRepository.existsByEmail(registerRecord.email())) {
                response.put("error", ResponseEntity.badRequest());
                response.put("message", "the email already exists");
                return response;
            }
            User client = new User(registerRecord.firstName(), registerRecord.lastName(), registerRecord.email(), registerRecord.password());
            UserRepository.save(client);
            response.put("Success",new UserDTO(client));
        }catch (Exception e){
            response.put("error", false);
            response.put("message", ResponseEntity.badRequest().build());
            return response;
        }
        return response;
    }
}
