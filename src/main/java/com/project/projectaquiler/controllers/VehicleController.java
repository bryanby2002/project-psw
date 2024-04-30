package com.project.projectaquiler.controllers;

import com.project.projectaquiler.dto.VehicleRequest;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.services.VehicleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
@Slf4j
public class VehicleController {

    private VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleEntity> registerVehicle(@RequestBody VehicleRequest vehicleRequest,
                                                         @RequestParam("imageUrl") MultipartFile image) {

        try {
            VehicleEntity saveVehicle = vehicleService.saveVehicleEntity(vehicleRequest, image);
            return new ResponseEntity<>(saveVehicle, HttpStatus.CREATED);
        }catch (IOException e){
            log.warn("Error occured while uploading image", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
