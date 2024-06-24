package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.details.BookingDetails;
import com.project.projectaquiler.dto.details.UserEntityDetails;
import com.project.projectaquiler.services.UserService;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

  private final UserService userService;


  @GetMapping("/list")
  public ResponseEntity<List<UserEntityDetails>> findAllUsers() {
    return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
  }
}
