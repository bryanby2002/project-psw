package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.request.BookingRequest;
import com.project.projectaquiler.persistence.entities.BookingEntity;
import com.project.projectaquiler.services.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
public class BookingController {

  private final BookingService bookingService;

  @PostMapping(value = "/create")
  public ResponseEntity<BookingEntity> saveBookingEntity(
    @RequestBody @Valid BookingRequest bookingRequest,
    @RequestParam("userid") String userId,
    @RequestParam("vehicleid") String vehicleId
  ) {
    return new ResponseEntity<>(
      bookingService.saveBooking(userId, vehicleId, bookingRequest),
      HttpStatus.CREATED
    );
  }
}
