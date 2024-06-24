package com.project.projectaquiler.dto.request;

import lombok.Data;

import java.time.LocalDate;

public record BookingRequest(
         LocalDate startDate,
         LocalDate endDate,
         String purpose
) {


}
