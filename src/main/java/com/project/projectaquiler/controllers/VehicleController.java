package com.project.projectaquiler.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.projectaquiler.dto.request.VehicleRequest;
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

    @PostMapping(value = "/create", consumes = {"multipart/form-data"})
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

    @GetMapping(value = "/list")
    public ResponseEntity<Iterable<VehicleEntity>> getAllVehicles() {
        return new ResponseEntity<>(vehicleService.findAllVehicles(), HttpStatus.OK);
    }

    @GetMapping("/search/{palabra}")
    public ResponseEntity<?> getVehiclesByPalabra(@PathVariable String palabra) {
        return new ResponseEntity<>(vehicleService.searchVehicleByPalabra(palabra), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<?> filterVehicles(@RequestParam("minPrice") Double minPrice,
                                              @RequestParam("maxPrice") Double maxPrice) {
        return new ResponseEntity<>(vehicleService.filterVehiclesForPrice(minPrice, maxPrice),
                HttpStatus.OK);
    }

}
