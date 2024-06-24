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

import java.util.Optional;

@RestController
@RequestMapping("/vehicle")
@AllArgsConstructor
public class VehicleController {

  private final VehicleService vehicleService;

  // create vehicle
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

  //get all vehicles
  @GetMapping(value = "/list")
  public ResponseEntity<Iterable<VehicleEntity>> getAllVehicles() {
    return new ResponseEntity<>(
      vehicleService.findAllVehicles(),
      HttpStatus.OK
    );
  }

  //search vehicles
  @GetMapping("/search/{palabra}")
  public ResponseEntity<?> getVehiclesByPalabra(@PathVariable String palabra) {
    return new ResponseEntity<>(
      vehicleService.searchVehicleByPalabra(palabra),
      HttpStatus.OK
    );
  }

  // filter price vehicles by price min to max
  @GetMapping("/filter/min")
  public ResponseEntity<?> shortVehiclesMinToMax() {
    return new ResponseEntity<>(
      vehicleService.shortVehiclesMinToMaxFilter(),
      HttpStatus.OK
    );
  }

  // filter price vehicles by price max to min
  @GetMapping("/filter/max")
  public ResponseEntity<?> shortVehiclesMaxToMin() {
    return new ResponseEntity<>(
            vehicleService.shortVehiclesMaxToMinFilter(),
            HttpStatus.OK
    );
  }

  // type vechicle
  @GetMapping("/type/{type}")
  public ResponseEntity<?> findVehicleByType(@PathVariable String type) {
    return new ResponseEntity<>(vehicleService.filterVehiclesByType(type), HttpStatus.OK);
  }

  //update vehicle for id
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

  @GetMapping("/{id}")
  public ResponseEntity<?> getVehicleById(@PathVariable String id){
    Optional<VehicleEntity> vehicle = vehicleService.getById(id);
    return vehicle.isPresent() ?
            new ResponseEntity<>(vehicle.get(), HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<?> deleteVehicleById(@PathVariable("id") String id){
    Optional<VehicleEntity> vehicle = vehicleService.getById(id);
    if(vehicle.isPresent()){
      vehicleService.deleteById(id);
      return new ResponseEntity<>("Successfull delete vehicle entity", HttpStatus.OK);
    }
    return new ResponseEntity<>("Vehicle not found", HttpStatus.NOT_FOUND);
  }
}
