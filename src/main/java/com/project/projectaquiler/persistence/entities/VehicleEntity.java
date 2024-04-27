package com.project.projectaquiler.persistence.entities;

import jakarta.persistence.*;
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

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String brand;
    private String model;
    private String color;
    private Integer year;
    private Double price;
    private String description;
    private String image;
    private String tuition;
    private Integer status;

}
