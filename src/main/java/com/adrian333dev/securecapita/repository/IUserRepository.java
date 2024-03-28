package com.adrian333dev.securecapita.repository;

import java.util.Collection;

import com.adrian333dev.securecapita.domain.User;

public interface IUserRepository<T extends User> {
  // BASIC CRUD OPERATIONS
  T create(T data);

  Collection<T> list(int limit, int offset);

  T get(Long id);

  T update(T data);

  void delete(Long id);

  // MORE COMPLEX OPERATIONS
}
