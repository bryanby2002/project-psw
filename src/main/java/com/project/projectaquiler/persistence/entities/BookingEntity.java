package com.project.projectaquiler.persistence.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookins")
public class BookingEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private VehicleEntity vehicle;
    private UserEntity user;
    private LocalDate startDate;
    private LocalDate endDate;
    private String purpose;
    private Double totalPrice;
}
