package com.adrian333dev.securecapita.query;

public class UserQuery {
  // Tables: users, roles, user_roles, events, user_events, account_verifications, reset_password_verifications, two_factor_verifications
  public static final String COUNT_USER_EMAIL_QUERY = "SELECT COUNT(*) FROM users WHERE email = :email";
  public static final String INSERT_USER_QUERY = "INSERT INTO users (first_name, last_name, email, password) VALUES (:firstName, :lastName, :email, :password) RETURNING id";
  public static final String INSERT_ACCOUNT_VERIFICATION_QUERY = "INSERT INTO account_verifications (user_id, url) VALUES (:userId, :url)";
  public static final String SELECT_USER_BY_EMAIL_QUERY = "";
  public static final String DELETE_VERIFICATION_CODE_BY_USER_ID = "";
  public static final String INSERT_VERIFICATION_CODE_QUERY = "";
  public static final String SELECT_USER_BY_USER_CODE_QUERY = "";
  public static final String DELETE_CODE = "";
  public static final String SELECT_CODE_EXPIRATION_QUERY = "";
  public static final String DELETE_PASSWORD_VERIFICATION_BY_USER_ID_QUERY = "";
  public static final String INSERT_PASSWORD_VERIFICATION_QUERY = "";
  public static final String SELECT_EXPIRATION_BY_URL = "";
  public static final String SELECT_USER_BY_PASSWORD_URL_QUERY = "";
  public static final String UPDATE_USER_PASSWORD_BY_URL_QUERY = "";
  public static final String DELETE_VERIFICATION_BY_URL_QUERY = "";
  public static final String SELECT_USER_BY_ACCOUNT_URL_QUERY = "";
  public static final String UPDATE_USER_ENABLED_QUERY = "";
  public static final String UPDATE_USER_DETAILS_QUERY = "";
  public static final String SELECT_USER_BY_ID_QUERY = "";
  public static final String UPDATE_USER_PASSWORD_BY_ID_QUERY = "";
  public static final String UPDATE_USER_SETTINGS_QUERY = "";
  public static final String TOGGLE_USER_MFA_QUERY = "";
  public static final String UPDATE_USER_IMAGE_QUERY = "";
  public static final String UPDATE_USER_PASSWORD_BY_USER_ID_QUERY = "";
}
