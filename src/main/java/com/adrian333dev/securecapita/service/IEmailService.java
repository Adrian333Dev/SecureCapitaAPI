package com.adrian333dev.securecapita.service;

import com.adrian333dev.securecapita.enums.VerificationType;

public interface IEmailService {
  void sendVerificationEmail(String firstName, String email, String verificationUrl, VerificationType verificationType);
}
