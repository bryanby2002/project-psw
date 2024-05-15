package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.request.VehicleRequest;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.persistence.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CloudinaryService cloudinaryService;

    // Metodo para registrar un vehiculo
    public VehicleEntity saveVehicleEntity(VehicleRequest request, MultipartFile imageFile) {

        try {
            String imageUrl = cloudinaryService.uploadImage(imageFile);
            VehicleEntity vehicleEntity = VehicleEntity.builder()
                    .brand(request.brand())
                    .model(request.model())
                    .color(request.color())
                    .year(request.year())
                    .price(request.price())
                    .description(request.description())
                    .imageUrl(imageUrl)
                    .tuition(request.tuition())
                    .status(1)
                    .build();
            log.info("Save vehicle entity: {}", vehicleEntity);
            return vehicleRepository.save(vehicleEntity);
        }catch (Exception e){
            log.info("error while saving vehicle entity: {}", e.getMessage());
            return VehicleEntity.builder()
                    .description("Error savin vehicle entity "+e.getMessage())
                    .build();
        }
    }

    // listar todos los vehiculos
    public Iterable<VehicleEntity> findAllVehicles(){
        return vehicleRepository.findAll();
    }

    //buscar un vehiculo por palbara
    public List<VehicleEntity> searchVehicleByPalabra(String palabra){
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false)
                .filter(vehicle -> vehicle.getBrand().equals(palabra) ||
                        vehicle.getModel().equals(palabra) ||
                        vehicle.getColor().equals(palabra))
                .toList();
    }

    // filtrar vehiculos por precio
    public List<VehicleEntity> filterVehiclesForPrice(Double maxPrice){
        return StreamSupport.stream(vehicleRepository.findAll().spliterator(), false)
                .filter(vehicle -> vehicle.getPrice()<=maxPrice)
                .toList();
    }

    // update vehicle by ID
    public void updateVehicleById(String vehicleId, VehicleRequest vehicleRequest){
        Optional<VehicleEntity> vehicleEntity = vehicleRepository.findById(vehicleId);
        if(vehicleEntity.isPresent()){
            VehicleEntity existingVehicle = vehicleEntity.get();
            existingVehicle.setBrand(vehicleRequest.brand());
            existingVehicle.setModel(vehicleRequest.model());
            existingVehicle.setColor(vehicleRequest.color());
            existingVehicle.setYear(vehicleRequest.year());
            existingVehicle.setPrice(vehicleRequest.price());
            existingVehicle.setDescription(vehicleRequest.description());
            existingVehicle.setImageUrl(vehicleRequest.image());
            existingVehicle.setStatus(vehicleRequest.status());
            vehicleRepository.save(existingVehicle);
        } else {
            log.info("Vehicle with id {} not found", vehicleId);
        }
    }
}
