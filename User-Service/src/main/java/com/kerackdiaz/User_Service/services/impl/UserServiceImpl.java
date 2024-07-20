package com.kerackdiaz.User_Service.services.impl;

import com.kerackdiaz.User_Service.dtos.LoginRecord;
import com.kerackdiaz.User_Service.dtos.RegisteredDTO;
import com.kerackdiaz.User_Service.dtos.RegisterRecord;
import com.kerackdiaz.User_Service.dtos.UserDTO;
import com.kerackdiaz.User_Service.models.User;
import com.kerackdiaz.User_Service.repositories.UserRepository;
import com.kerackdiaz.User_Service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository UserRepository;

    @Override
    public RegisteredDTO Register(RegisterRecord registerRecord) throws Exception {
        try {
            if (registerRecord.firstName().isBlank()) {
                throw new Exception("the first name are empty");
            }
            if (registerRecord.lastName().isBlank()) {
                throw new Exception("the last name are empty");
            }
            if (registerRecord.email().isBlank()) {
                throw new Exception("the email are empty");
            }
            if (registerRecord.password().isBlank()) {
                throw new Exception("the password are empty");
            }
            if (UserRepository.existsByEmail(registerRecord.email())) {
                throw new Exception("the email already exists");
            }
            User client = new User(registerRecord.firstName(), registerRecord.lastName(), registerRecord.email(), registerRecord.password());
            client.setRole("USER");
            UserRepository.save(client);
            return new RegisteredDTO(client);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public UserDTO Login(LoginRecord loginRecord) throws Exception {
        try {
            if (loginRecord.email().isBlank()) {
                throw new Exception("the email are empty");
            }
            if (loginRecord.password().isBlank()) {
                throw new Exception("the password are empty");
            }
            User client = UserRepository.findByEmail(loginRecord.email());
            if (client == null) {
                throw new Exception("the email does not exist");
            }
            if (!BCrypt.checkpw(loginRecord.password(), client.getPassword())) {
                throw new Exception("the password is incorrect");
            }
            return new UserDTO(client);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
