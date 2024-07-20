package com.kerackdiaz.User_Service.services;

import com.kerackdiaz.User_Service.dtos.RegisterRecord;
import com.kerackdiaz.User_Service.dtos.RegisteredDTO;

public interface UserService {

    RegisteredDTO Register(RegisterRecord registerRecord) throws Exception;
}
