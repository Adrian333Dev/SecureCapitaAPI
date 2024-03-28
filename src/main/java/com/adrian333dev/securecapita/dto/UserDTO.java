package com.adrian333dev.securecapita.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDTO {
  private Long id;
  private String firstName;
  private String lastName;
  private String email;
  private String address;
  private String phone;
  private String title;
  private String bio;
  private String imageUrl;
  private boolean isActive;
  private boolean isLocked;
  private boolean isMfaEnabled;
  private String createdAt;
}
