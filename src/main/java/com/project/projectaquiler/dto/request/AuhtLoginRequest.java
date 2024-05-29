package com.project.projectaquiler.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AuhtLoginRequest(
  @NotBlank String username,
  @NotBlank String password
) {}
