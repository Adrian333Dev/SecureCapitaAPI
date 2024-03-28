package com.adrian333dev.securecapita.service.implementation;

import org.springframework.stereotype.Service;

import com.adrian333dev.securecapita.domain.User;
import com.adrian333dev.securecapita.dto.UserDTO;
import com.adrian333dev.securecapita.dtomapper.UserDTOMapper;
import com.adrian333dev.securecapita.repository.IUserRepository;
import com.adrian333dev.securecapita.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
  private final IUserRepository<User> userRepository;

  @Override
  public UserDTO createUser(User user) {
    return UserDTOMapper.fromUser(userRepository.create(user));
  }

}
