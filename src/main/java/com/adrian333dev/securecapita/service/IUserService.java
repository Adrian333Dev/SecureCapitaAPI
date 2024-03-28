package com.adrian333dev.securecapita.service;

import com.adrian333dev.securecapita.domain.User;
import com.adrian333dev.securecapita.dto.UserDTO;

public interface IUserService {
  UserDTO createUser(User user);
}
