package com.adrian333dev.securecapita.enums;

public enum VerificationType {
  ACCOUNT("ACCOUNT"),
  PASSWORD("PASSWORD");

  private final String type;

  VerificationType(String type) {
    this.type = type;
  }

  public String getType() {
    return type.toLowerCase();
  }
}
