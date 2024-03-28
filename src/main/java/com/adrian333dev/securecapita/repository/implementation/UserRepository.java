package com.adrian333dev.securecapita.repository.implementation;

import com.adrian333dev.securecapita.domain.Role;
import com.adrian333dev.securecapita.domain.User;
import com.adrian333dev.securecapita.enums.VerificationType;
// import com.adrian333dev.securecapita.domain.UserPrincipal;
// import com.adrian333dev.securecapita.dto.UserDTO;
// import com.adrian333dev.securecapita.enums.VerificationType;
import com.adrian333dev.securecapita.exception.ApiException;
// import com.adrian333dev.securecapita.form.UpdateForm;
import com.adrian333dev.securecapita.repository.IRoleRepository;
import com.adrian333dev.securecapita.repository.IUserRepository;
import com.adrian333dev.securecapita.service.IEmailService;

// import com.adrian333dev.securecapita.rowmapper.UserRowMapper;
// import com.adrian333dev.securecapita.service.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
// import org.springframework.web.multipart.MultipartFile;
// import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

// import java.io.IOException;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.Collection;
// import java.util.Date;
import java.util.Map;
import java.util.UUID;
// import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletableFuture;

import static com.adrian333dev.securecapita.enums.RoleType.ROLE_USER;
import static com.adrian333dev.securecapita.enums.VerificationType.ACCOUNT;
// import static com.adrian333dev.securecapita.enums.VerificationType.PASSWORD;
import static com.adrian333dev.securecapita.query.UserQuery.*;
// import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.util.Collections.singletonMap;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;
// import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
// import static org.apache.commons.lang3.StringUtils.isBlank;
// import static org.apache.commons.lang3.time.DateFormatUtils.format;
// import static org.apache.commons.lang3.time.DateUtils.addDays;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepository implements IUserRepository<User> {

  private final NamedParameterJdbcTemplate jdbc;
  private final IRoleRepository<Role> roleRepository;
  private final BCryptPasswordEncoder encoder;
  private final IEmailService emailService;

  @Override
  public User create(User user) {
    if (getEmailCount(user.getEmail().trim().toLowerCase()) > 0)
      throw new ApiException("Email already in use. Please use a different email.");

    try {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      SqlParameterSource params = getSqlParamsSource(user);
      if (params == null)
        throw new ApiException("Error saving user to database");
      jdbc.update(INSERT_USER_QUERY, params, keyHolder);
      user.setId(requireNonNull(keyHolder.getKey()).longValue());
      roleRepository.assignRole(user.getId(), ROLE_USER.name());
      String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
      Map<String, Object> verificationParams = of("userId", user.getId(), "url", verificationUrl);
      if (verificationParams == null)
        throw new ApiException("Error saving user to database");
      jdbc.update(INSERT_ACCOUNT_VERIFICATION_QUERY, verificationParams);
      sendEmail(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);
      user.setActive(false);
      user.setLocked(false);
      return user;
    } catch (Exception e) {
      log.error("Error saving user to database", e);
      throw new ApiException("Error saving user to database", e);
    }
  }

  @Override
  public Collection<User> list(int limit, int offset) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'list'");
  }

  @Override
  public User get(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'get'");
  }

  @Override
  public User update(User user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'update'");
  }

  @Override
  public void delete(Long id) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'delete'");
  }

  // Helper methods
  private Integer getEmailCount(String email) {
    Map<String, Object> params = singletonMap("email", email);
    if (params == null)
      throw new ApiException("Error preparing parameters for querying email count");
    return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, params, Integer.class);
  }

  private SqlParameterSource getSqlParamsSource(User user) {
    return new MapSqlParameterSource()
        .addValue("firstName", user.getFirstName())
        .addValue("lastName", user.getLastName())
        .addValue("email", user.getEmail())
        .addValue("password", encoder.encode(user.getPassword()));
  }

  private String getVerificationUrl(String key, String type) {
    return fromCurrentContextPath().path("/user/verify/" + type + "/" + key).toUriString();
  }

  private void sendEmail(String firstName, String email, String verificationUrl, VerificationType verificationType) {
    CompletableFuture.runAsync(
        () -> emailService.sendVerificationEmail(firstName, email, verificationUrl, verificationType));
  }
}
