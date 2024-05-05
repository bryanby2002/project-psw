package com.project.projectaquiler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projectaquiler.dto.VehicleRequest;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.services.VehicleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @PostMapping(consumes = {"multipart/form-data"})
    public ResponseEntity<VehicleEntity> registerVehicle(
            @RequestParam("vehicleRequest") String vehicleRequestJson,
            @RequestParam("image") MultipartFile image) throws JsonProcessingException {

        // Convertir el JSON de vehicleRequest a un objeto VehicleRequest
        ObjectMapper objectMapper = new ObjectMapper();
        VehicleRequest vehicleRequest = objectMapper.readValue(vehicleRequestJson, VehicleRequest.class);

        // Guardar el vehículo con la imagen
        VehicleEntity saveVehicle = vehicleService.saveVehicleEntity(vehicleRequest, image);
        return new ResponseEntity<>(saveVehicle, HttpStatus.CREATED);
    }
}
