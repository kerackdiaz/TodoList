package com.kerackdiaz.User_Service.services;

import com.kerackdiaz.User_Service.dtos.LoginRecord;
import com.kerackdiaz.User_Service.dtos.RegisterRecord;
import com.kerackdiaz.User_Service.dtos.RegisteredDTO;
import com.kerackdiaz.User_Service.dtos.UserDTO;

public interface UserService {

    RegisteredDTO Register(RegisterRecord registerRecord) throws Exception;

    UserDTO Login(LoginRecord loginRecord) throws Exception;
}
