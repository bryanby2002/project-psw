package com.project.projectaquiler.dto;

import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.entities.VehicleEntity;

import java.time.LocalDate;

public record BookingRequest
        (
                UserEntity user,
                VehicleEntity vehicle,
                LocalDate startDate,
                LocalDate endDate,
                String purpose,
                Double totalPrice
        )
{
}
