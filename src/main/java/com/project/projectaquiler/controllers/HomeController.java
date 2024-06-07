package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.request.AuthLoginRequest;
import com.project.projectaquiler.dto.request.AuthResponse;
import com.project.projectaquiler.dto.request.UserRequest;
import com.project.projectaquiler.services.auth.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class HomeController {

  private final UserDetailsImpl userDetailsImpl;

  // crear usuario
  @PostMapping(value = "/sing-up")
  public ResponseEntity<AuthResponse> createUser(
    @RequestBody @Valid UserRequest user) {
    return new ResponseEntity<>(
      userDetailsImpl.createUser(user),
      HttpStatus.CREATED
    );
  }

  // inicion de sesion usuario
  @PostMapping("/log-in")
  public ResponseEntity<AuthResponse> login(
    @RequestBody @Valid AuthLoginRequest request) {
    return new ResponseEntity<>(
      userDetailsImpl.loginUser(request),
      HttpStatus.OK
    );
  }
}
