package com.kerackdiaz.User_Service.services;

import com.kerackdiaz.User_Service.dtos.RegisterRecord;

import java.util.Map;

public interface UserService {

    Map<String,Object> Register(RegisterRecord registerRecord);
}
