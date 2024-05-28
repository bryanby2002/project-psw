package com.project.projectaquiler.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
@Builder
public class VehicleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private String id;

  private String brand;

  @Column(unique = true)
  private String model;
  private String typeVehicle;
  private String color;
  private Integer year;
  private Double price;
  private String description;
  private String imageUrl;
  private String plate;
  private Integer passengerCapacity;
  private String vehicleStatus;

  @OneToMany(
    fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "vehicle",
    targetEntity = BookingEntity.class
  )
  @JsonBackReference("booking-vehicle")
  @Builder.Default
  private List<BookingEntity> bookingEntityList = new ArrayList<>();
}
