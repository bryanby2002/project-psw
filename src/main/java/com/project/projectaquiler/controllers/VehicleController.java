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

  @PostMapping(value = "/create", consumes = { "multipart/form-data" })
  public ResponseEntity<?> registerVehicle(
    @RequestParam("vehicleRequest") String vehicleRequestJson,
    @RequestParam("image") MultipartFile image
  ) throws JsonProcessingException {
    try {
      // Convertir el JSON de vehicleRequest a un objeto VehicleRequest
      ObjectMapper objectMapper = new ObjectMapper();
      VehicleRequest vehicleRequest = objectMapper.readValue(
        vehicleRequestJson,
        VehicleRequest.class
      );

      // Guardar el vehículo con la imagen
      VehicleEntity saveVehicle = vehicleService.saveVehicleEntity(
        vehicleRequest,
        image
      );
      return new ResponseEntity<>(saveVehicle, HttpStatus.CREATED);
    } catch (Exception e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @GetMapping(value = "/list")
  public ResponseEntity<Iterable<VehicleEntity>> getAllVehicles() {
    return new ResponseEntity<>(
      vehicleService.findAllVehicles(),
      HttpStatus.OK
    );
  }

  @GetMapping("/search/{palabra}")
  public ResponseEntity<?> getVehiclesByPalabra(@PathVariable String palabra) {
    return new ResponseEntity<>(
      vehicleService.searchVehicleByPalabra(palabra),
      HttpStatus.OK
    );
  }

  @GetMapping("/filter")
  public ResponseEntity<?> filterVehiclesByMaxPrice(
    @RequestParam("maxPrice") Double maxPrice
  ) {
    return new ResponseEntity<>(
      vehicleService.filterVehiclesForPrice(maxPrice),
      HttpStatus.OK
    );
  }

  @PatchMapping("/update/{vehicleId}")
  public ResponseEntity<?> updateVehicle(
    @PathVariable String vehicleId,
    @RequestBody VehicleRequest vehicleRequest
  ) {
    vehicleService.updateVehicleById(vehicleId, vehicleRequest);
    return new ResponseEntity<>(
      "Successfull update vehicle entity",
      HttpStatus.CREATED
    );
  }
}
