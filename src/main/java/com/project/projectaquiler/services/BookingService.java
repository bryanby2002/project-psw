package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.exceptions.BookingException;
import com.project.projectaquiler.dto.request.BookingRequest;
import com.project.projectaquiler.persistence.entities.BookingEntity;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.persistence.repositories.BookingRespository;
import com.project.projectaquiler.persistence.repositories.UserRepository;
import com.project.projectaquiler.persistence.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BookingService {

  private final BookingRespository bookingRespository;
  private final UserRepository userRepository;
  private final VehicleRepository vehicleRepository;

  // guardar reserva
  public BookingEntity saveBooking(
    String vehicleId,
    BookingRequest bookingRequest
  ) {
    VehicleEntity vehicle = vehicleRepository
      .findById(vehicleId)
      .orElseThrow(() -> new RuntimeException("Vehicle not found"));

    //validar fechas de inicio y fin
    if (bookingRequest.endDate().isBefore(bookingRequest.startDate())) {
      throw new BookingException("End date must be after start date");
    }

    BookingEntity bookingEntity = BookingEntity
      .builder()
      .vehicle(vehicle)
      .startDate(bookingRequest.startDate())
      .endDate(bookingRequest.endDate())
      .purpose(bookingRequest.purpose())
      .totalPrice(vehicle.getPrice())
      .build();
    return bookingRespository.save(bookingEntity);
  }
}
