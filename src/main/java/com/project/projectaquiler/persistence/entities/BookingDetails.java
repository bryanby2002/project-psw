package com.project.projectaquiler.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDetails {

    private String idUser;
    private String imageUrl;
    private double totalPrice;
    private String brand;
    private LocalDate startDate;
    private LocalDate endDate;
}
