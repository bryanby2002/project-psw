package com.project.projectaquiler.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRequest(
                LocalDate startDate,
                LocalDate endDate,
                @NotNull @NotBlank String purpose) {
}
