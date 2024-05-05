package com.project.projectaquiler.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record VehicleRequest
        (
                @NotNull @NotBlank String brand,
                @NotNull @NotBlank String model,
                @NotNull @NotBlank String color,
                Integer year,
                Double price,
                @NotNull @NotBlank String description,
                @NotNull @NotBlank String image,
                @NotNull @NotBlank String tuition,
                @NotNull @NotBlank String status
        )
{
}
