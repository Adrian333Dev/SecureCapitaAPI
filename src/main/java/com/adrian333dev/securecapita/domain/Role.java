package com.adrian333dev.securecapita.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@SuperBuilder
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class Role {
  private Long id;
  private String name;
  private String permission;
}
