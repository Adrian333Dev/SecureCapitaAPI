package com.adrian333dev.securecapita.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

import java.util.Map;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@JsonInclude(NON_DEFAULT)
public class HttpResponse {
  protected String timestamp;
  protected int statusCode;
  protected HttpStatus status;
  protected String cause;
  protected String message;
  protected String developerMessage;
  protected Map<?, ?> data;
}
