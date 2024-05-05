package com.project.projectaquiler.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
@Builder
public class BookingEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonManagedReference("booking-user")
    private UserEntity user;

    @ManyToOne(targetEntity = VehicleEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id", nullable = false)
    @JsonManagedReference("booking-vehicle")
    private VehicleEntity vehicle;
    private LocalDate startDate;
    private LocalDate endDate;
    private String purpose;
    private Double totalPrice;
}
