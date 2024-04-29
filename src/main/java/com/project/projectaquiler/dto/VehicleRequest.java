package com.project.projectaquiler.dto;

public record VehicleRequest
        (
                String brand,
                String model,
                String color,
                Integer year,
                Double price,
                String description,
                String image,
                String tuition,
                String status
        )
{
}
