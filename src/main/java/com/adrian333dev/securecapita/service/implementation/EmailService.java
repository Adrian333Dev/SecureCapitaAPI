package com.adrian333dev.securecapita.service.implementation;

import org.springframework.stereotype.Service;

import com.adrian333dev.securecapita.enums.VerificationType;
import com.adrian333dev.securecapita.service.IEmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService implements IEmailService {

  @Override
  public void sendVerificationEmail(String firstName, String email, String verificationUrl,
      VerificationType verificationType) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'sendVerificationEmail'");
  }

}
