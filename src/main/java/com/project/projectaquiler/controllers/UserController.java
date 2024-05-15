package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.details.BookingDetails;
import com.project.projectaquiler.dto.details.UserEntityDetails;
import com.project.projectaquiler.dto.request.UserRequest;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping(value = "/create")
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserRequest user) {
        return new ResponseEntity<>(userService.saveUserEntity(user), HttpStatus.CREATED);
    }

    @GetMapping("/bookings")
    public ResponseEntity<List<BookingDetails>> findBookingForUser(
            @RequestParam("username") String username) {
        try {
            return new ResponseEntity<>(userService.findBookingsForUser(username), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>( HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<UserEntityDetails>> findAllUsers() {
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

}
