package com.project.projectaquiler.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
public class BookingEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(targetEntity = UserEntity.class)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference("booking-user")
    @NotNull @NotBlank
    private UserEntity user;

    @ManyToOne(targetEntity = VehicleEntity.class)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @JsonManagedReference("booking-vehicle")
    @NotNull @NotBlank
    private VehicleEntity vehicle;

    @NotNull @NotBlank
    private LocalDate startDate;

    @NotNull @NotBlank
    private LocalDate endDate;

    @NotNull @NotBlank
    private String purpose;

    @NotNull @NotBlank
    private Double totalPrice;
}
