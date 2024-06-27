package com.project.projectaquiler.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "bookings")
@Builder
public class BookingEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  @ManyToOne(targetEntity = VehicleEntity.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "vehicle_id", nullable = false)
  @JsonManagedReference("booking-vehicle")
  private VehicleEntity vehicle;

  private LocalDate startDate;
  private LocalDate endDate;
  private String purpose;
  private Double totalPrice;
  private String vehicleType;
  private LocalDateTime bookingDate;
  private String vehicleImage;
  private String vehiclePlate;
  private Integer vehicleCapacity;
  private String vehicleModel;
  private Integer vehicleYear;
  private String vehicleBrand;


}
