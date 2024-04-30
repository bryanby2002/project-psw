package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.BookingRequest;
import com.project.projectaquiler.persistence.entities.BookingEntity;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.persistence.repositories.BookingRespository;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import com.project.projectaquiler.persistence.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

    private final BookingRespository bookingRespository;
    private final UserRepository userRepository;
    private final VehicleRepository vehicleRepository;

    public BookingEntity saveBooking(String userId, String vehicleId, BookingRequest bookingRequest){

        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        VehicleEntity vehicle = vehicleRepository.findById(vehicleId)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        BookingEntity bookingEntity = BookingEntity.builder()
                .user(user)
                .vehicle(vehicle)
                .startDate(bookingRequest.startDate())
                .endDate(bookingRequest.endDate())
                .purpose(bookingRequest.purpose())
                .totalPrice(vehicle.getPrice())
                .build();
        return bookingRespository.save(bookingEntity);
    }
}
