package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.request.VehicleRequest;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.persistence.repositories.VehicleRepository;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CloudinaryService cloudinaryService;

    // Metodo para registrar un vehiculo
    public VehicleEntity saveVehicleEntity(
            VehicleRequest request,
            MultipartFile imageFile
    ) throws IOException {
        String imageUrl = cloudinaryService.uploadImage(imageFile);
        VehicleEntity vehicleEntity = VehicleEntity
                .builder()
                .brand(request.brand())
                .model(request.model())
                .color(request.color())
                .year(request.year())
                .price(request.price())
                .description(request.description())
                .imageUrl(imageUrl)
                .plate(request.plate())
                .typeVehicle(request.type())
                .passengerCapacity(request.passengerCapacity())
                .vehicleStatus(request.status())
                .build();
        log.info("Save vehicle entity: {}", vehicleEntity);
        return vehicleRepository.save(vehicleEntity);
    }

    // filter vehicle by type
    public List<VehicleEntity> filterVehiclesByType(String type) {
        return StreamSupport
                .stream(vehicleRepository.findAll().spliterator(), true)
                .filter(vechicle -> vechicle.getTypeVehicle().equals(type))
                .toList();
    }

    // listar todos los vehiculos
    public Iterable<VehicleEntity> findAllVehicles() {
        return vehicleRepository.findAll();
    }

    //buscar un vehiculo por palabara
    public List<VehicleEntity> searchVehicleByPalabra(String palabra) {
        return vehicleRepository.seachByParam(palabra);
    }

    // filtrar vehiculos por precio menor a mayor
    public List<VehicleEntity> shortVehiclesMinToMaxFilter() {
        return StreamSupport
                .stream(vehicleRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingDouble(VehicleEntity::getPrice))
                .toList();
    }

    // filtrar vehiculos por precio mayor a menor
    public List<VehicleEntity> shortVehiclesMaxToMinFilter() {
        return StreamSupport
                .stream(vehicleRepository.findAll().spliterator(), false)
                .sorted(Comparator.comparingDouble(VehicleEntity::getPrice).reversed())
                .toList();
    }

    // update vehicle by ID
    public void updateVehicleById(
            String vehicleId,
            VehicleRequest vehicleRequest
    ) {
        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(
                vehicleId
        );
        if (vehicleEntity.isPresent()) {
            VehicleEntity existingVehicle = vehicleEntity.get();
            existingVehicle.setBrand(vehicleRequest.brand());
            existingVehicle.setModel(vehicleRequest.model());
            existingVehicle.setColor(vehicleRequest.color());
            existingVehicle.setYear(vehicleRequest.year());
            existingVehicle.setPrice(vehicleRequest.price());
            existingVehicle.setDescription(vehicleRequest.description());
            existingVehicle.setImageUrl(vehicleRequest.image());
            existingVehicle.setVehicleStatus(vehicleRequest.status());
            existingVehicle.setPlate(vehicleRequest.plate());
            existingVehicle.setPassengerCapacity(vehicleRequest.passengerCapacity());
            log.info("Update vehicle {}", existingVehicle);
            vehicleRepository.save(existingVehicle);
        } else {
            log.info("Vehicle with id {} not found", vehicleId);
        }
    }

    // getbyId
    public Optional<VehicleEntity> getById(String id){
        return vehicleRepository.findById(id);
    }
}
