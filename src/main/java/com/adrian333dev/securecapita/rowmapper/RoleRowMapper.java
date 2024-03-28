package com.adrian333dev.securecapita.rowmapper;

import com.adrian333dev.securecapita.domain.Role;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;

// import static java.util.Objects.requireNonNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
  @Override
  public Role mapRow(@NonNull ResultSet resultSet, int rowNum) throws SQLException {
    return Role.builder()
        .id(resultSet.getLong("id"))
        .name(resultSet.getString("name"))
        .permission(resultSet.getString("permission"))
        .build();
  }
}
