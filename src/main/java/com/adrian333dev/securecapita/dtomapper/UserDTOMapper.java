package com.adrian333dev.securecapita.dtomapper;

import org.springframework.stereotype.Component;

import com.adrian333dev.securecapita.domain.User;
import com.adrian333dev.securecapita.dto.UserDTO;

import static java.util.Objects.requireNonNull;
import static org.springframework.beans.BeanUtils.copyProperties;

@Component
public class UserDTOMapper {
  public static UserDTO fromUser(User user) {
    UserDTO userDTO = new UserDTO();
    copyProperties(requireNonNull(user, "User cannot be null"), userDTO);
    return userDTO;
  }

  public static User toUser(UserDTO userDTO) {
    User user = new User();
    copyProperties(requireNonNull(userDTO, "UserDTO cannot be null"), user);
    return user;
  }
}
