package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.exceptions.BookingException;
import com.project.projectaquiler.dto.request.BookingRequest;
import com.project.projectaquiler.persistence.entities.BookingEntity;
import com.project.projectaquiler.services.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
@Slf4j
public class BookingController {

  private final BookingService bookingService;

  @PostMapping("/create")
  public ResponseEntity<?> saveBookingEntity(
    @RequestBody @Valid BookingRequest bookingRequest,
    @RequestParam("userId") String userId,
    @RequestParam("vehicleId") String vehicleId
  ) {
    try {
      BookingEntity booking = bookingService.saveBooking(
        userId,
        vehicleId,
        bookingRequest
      );
      return ResponseEntity.status(HttpStatus.CREATED).body(booking);
    } catch (BookingException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    } catch (Exception e) {
      log.error("Error saving booking", e);
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }
}
