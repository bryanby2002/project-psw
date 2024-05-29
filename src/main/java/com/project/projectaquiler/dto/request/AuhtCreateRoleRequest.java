package com.project.projectaquiler.dto.request;

import jakarta.validation.constraints.Size;
import java.util.List;
import org.springframework.validation.annotation.Validated;

@Validated
public record AuhtCreateRoleRequest(
  @Size(max = 4, message = "El usuario no puede tener mas de 4 roles")
  List<String> roleListName
) {}
