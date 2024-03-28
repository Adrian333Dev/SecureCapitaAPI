package com.adrian333dev.securecapita.repository.implementation;

import java.util.Collection;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.adrian333dev.securecapita.domain.Role;
import com.adrian333dev.securecapita.exception.ApiException;
import com.adrian333dev.securecapita.repository.IRoleRepository;
import com.adrian333dev.securecapita.rowmapper.RoleRowMapper;

import static com.adrian333dev.securecapita.query.RoleQuery.*;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepository implements IRoleRepository<Role> {
  private final NamedParameterJdbcTemplate jdbc;

  @Override
  public Role create(Role data) {
    return null;
  }

  @Override
  public Collection<Role> list(int limit, int offset) {
    return null;
  }

  @Override
  public Role get(Long id) {
    return null;
  }

  @Override
  public Role update(Role data) {
    return null;
  }

  @Override
  public void delete(Long id) {
  }

  @Override
  public void assignRole(Long userId, String roleName) {
    log.info("Assigning role {} to user {}", roleName, userId);
    try {
      Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, requireNonNull(of("name", roleName)),
          new RoleRowMapper());
      if (role == null)
        throw new ApiException("No Role found by name : " + roleName);

      jdbc.update(INSERT_ROLE_TO_USER_QUERY,
          requireNonNull(of("userId", userId, "roleId", role.getId())));
    } catch (EmptyResultDataAccessException e) {
      throw new ApiException("No Role found by name : " + roleName);
    } catch (Exception e) {
      log.error("Error assigning role to user", e);
      throw new ApiException("Error assigning role to user", e);
    }
  }

  @Override
  public Role getRoleByUserId(Long userId) {
    return null;
  }

  @Override
  public Role getRoleByName(String roleName) {
    return null;
  }

  @Override
  public void updateUserRole(Long userId, String roleName) {
  }

}
