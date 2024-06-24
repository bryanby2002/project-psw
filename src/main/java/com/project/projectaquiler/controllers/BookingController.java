package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.exceptions.BookingException;
import com.project.projectaquiler.dto.request.BookingRequest;
import com.project.projectaquiler.persistence.entities.BookingEntity;
import com.project.projectaquiler.persistence.repositories.BookingRespository;
import com.project.projectaquiler.services.BookingService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/booking")
@AllArgsConstructor
@Slf4j
public class BookingController {

    private final BookingService bookingService;
    private final BookingRespository bookingRespository;

    @PostMapping("/create")
    public ResponseEntity<?> saveBookingEntity(
            @RequestBody @Valid BookingRequest bookingRequest,
            @RequestParam("vehicleId") String vehicleId) {

        try {
            BookingEntity booking = bookingService.saveBooking(vehicleId,bookingRequest);
            return new ResponseEntity<>(booking, HttpStatus.CREATED);
        } catch (BookingException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error("Error saving booking", e);
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllBookings() {
        var listBookings = bookingRespository.findAll();
        return new ResponseEntity<>(listBookings, HttpStatus.OK);
    }
}
