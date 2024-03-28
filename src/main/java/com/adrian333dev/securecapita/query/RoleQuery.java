package com.adrian333dev.securecapita.query;

public class RoleQuery {
  // Tables: users, roles, user_roles, events, user_events, account_verifications, reset_password_verifications, two_factor_verifications
  public static final String SELECT_ROLES_QUERY = "SELECT * FROM roles";
  public static final String INSERT_ROLE_TO_USER_QUERY = "INSERT INTO user_roles (user_id, role_id) VALUES (:userId, :roleId)";
  public static final String SELECT_ROLE_BY_NAME_QUERY = "SELECT * FROM roles WHERE name = :name";
  public static final String SELECT_ROLE_BY_ID_QUERY = "";
  public static final String UPDATE_USER_ROLE_QUERY = "";
}
