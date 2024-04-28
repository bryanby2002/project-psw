package com.project.projectaquiler.persistence.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicles")
@Builder
public class VehicleEntity {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String brand;
    @Column(unique = true)
    private String model;
    private String color;
    private Integer year;
    private Double price;
    private String description;
    private String image;
    private String tuition;
    private Integer status;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "vehicle",
            targetEntity = BookingEntity.class
    )
    @JsonBackReference("booking-vehicle")
    private List<BookingEntity> bookingEntityList = new ArrayList<>();

}
