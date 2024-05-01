package com.project.projectaquiler.dto;

import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record BookingRequest
        (
                @NotNull UserEntity user,
                @NotNull VehicleEntity vehicle,
                @NotNull LocalDate startDate,
                @NotNull LocalDate endDate,
                @NotNull @NotBlank String purpose,
                Double totalPrice
        )
{
}
