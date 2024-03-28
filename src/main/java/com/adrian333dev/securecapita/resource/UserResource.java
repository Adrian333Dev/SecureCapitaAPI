package com.adrian333dev.securecapita.resource;

// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;

// import com.adrian333dev.securecapita.domain.User;
// import com.adrian333dev.securecapita.dto.UserDTO;
// import com.adrian333dev.securecapita.domain.HttpResponse;
// import com.adrian333dev.securecapita.service.IUserService;

// import jakarta.validation.Valid;
// import static java.time.LocalDateTime.now;

// import static java.util.Map.of;

import com.adrian333dev.securecapita.domain.HttpResponse;
import com.adrian333dev.securecapita.domain.User;
// import com.adrian333dev.securecapita.domain.UserPrincipal;
import com.adrian333dev.securecapita.dto.UserDTO;
// import com.adrian333dev.securecapita.event.NewUserEvent;
// import com.adrian333dev.securecapita.exception.ApiException;
// import com.adrian333dev.securecapita.form.*;
// import com.adrian333dev.securecapita.provider.TokenProvider;
// import com.adrian333dev.securecapita.service.EventService;
// import com.adrian333dev.securecapita.service.RoleService;
import com.adrian333dev.securecapita.service.IUserService;
// import jakarta.servlet.http.HttpServletRequest;
// import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

// import lombok.RequiredArgsConstructor;
// import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
// import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
// import java.nio.file.Files;
// import java.nio.file.Paths;
// import java.util.concurrent.TimeUnit;

// import static com.adrian333dev.securecapita.dtomapper.UserDTOMapper.toUser;
// import static com.adrian333dev.securecapita.enums.EventType.*;
// import static com.adrian333dev.securecapita.utils.ExceptionUtils.processError;
// import static com.adrian333dev.securecapita.utils.UserUtils.getAuthenticatedUser;
// import static com.adrian333dev.securecapita.utils.UserUtils.getLoggedInUser;
import static java.time.LocalDateTime.now;
import static java.util.Map.of;
import static java.util.Objects.requireNonNull;
// import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.*;
// import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;
// import static org.springframework.security.authentication.UsernamePasswordAuthenticationToken.unauthenticated;
// import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;
import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentContextPath;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserResource {
  private final IUserService userService;

  @SuppressWarnings("null")
  @PostMapping("/register")
  public ResponseEntity<HttpResponse> saveUser(@RequestBody @Valid User user) {
    UserDTO userDTO = userService.createUser(user);
    return ResponseEntity.created(getUri()).body(
        HttpResponse.builder()
            .timestamp(now().toString())
            .data(of("user", userDTO))
            .message(String.format("User account created for user %s", user.getFirstName()))
            .status(CREATED)
            .statusCode(CREATED.value())
            .build());
  }

  // Helper methods
  private URI getUri() {
    URI uri = URI.create(fromCurrentContextPath().path("/user/get/<userId>").toUriString());
    return requireNonNull(uri, "URI must not be null");
  }
}