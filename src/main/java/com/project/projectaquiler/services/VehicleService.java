package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.request.VehicleRequest;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.persistence.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CloudinaryService cloudinaryService;

    // Meotodo para registrar un vehiculo
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
        }catch (DataIntegrityViolationException e){
            log.info("error while saving vehicle entity: {}", e.getMessage());
            return VehicleEntity.builder()
                    .description("NO se pudo registrar XDXDXDD")
                    .build();
        } catch (IOException e) {
            log.info("error while saving file  : {}", e.getMessage());
        }
        return null;
    }

    public Iterable<VehicleEntity> findAllVehicles(){
        return vehicleRepository.findAll();
    }
}
