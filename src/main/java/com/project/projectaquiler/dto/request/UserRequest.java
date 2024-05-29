package com.project.projectaquiler.dto.request;

import jakarta.validation.*;
import jakarta.validation.constraints.*;

@Valid
public record UserRequest(
  @NotNull @NotBlank String userName,
  @NotNull @NotBlank String password,
  @Digits(integer = 8, fraction = 0) Integer dni,
  @NotNull @NotBlank @Email String email,
  @NotNull @NotBlank String name,
  String lastName,
  @Digits(integer = 9, fraction = 0) Integer phone,
  Integer age,
  String gender,
  @NotNull @NotBlank String address,
  @Valid AuhtCreateRoleRequest roleRequest

) {
}
