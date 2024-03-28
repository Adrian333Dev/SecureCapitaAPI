package com.adrian333dev.securecapita.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class User {
  private Long id;
  @NotEmpty(message = "First name is required")
  private String firstName;
  @NotEmpty(message = "Last name is required")
  private String lastName;
  @NotEmpty(message = "Email is required")
  @Email(message = "Email is invalid")
  private String email;
  @NotEmpty(message = "Password is required")
  private String password;
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
