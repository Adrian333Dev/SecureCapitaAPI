package com.adrian333dev.securecapita.repository;

import java.util.Collection;

import com.adrian333dev.securecapita.domain.Role;

public interface IRoleRepository<T extends Role> {
  // BASIC CRUD OPERATIONS
  T create(T data);

  Collection<T> list(int limit, int offset);

  T get(Long id);

  T update(T data);

  void delete(Long id);

  // MORE COMPLEX OPERATIONS
  void assignRole(Long userId, String roleName);
  Role getRoleByUserId(Long userId);
  Role getRoleByName(String roleName);
  void updateUserRole(Long userId, String roleName);
}
