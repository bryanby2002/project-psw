package com.project.projectaquiler.persistence.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
}
