package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.UserRequest;
import com.project.projectaquiler.persistence.entities.UserEntity;
import com.project.projectaquiler.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping()
    public ResponseEntity<UserEntity> createUser(@RequestBody @Valid UserRequest user) {
        return new ResponseEntity<>(userService.saveUserEntity(user), HttpStatus.CREATED);
    }
}
