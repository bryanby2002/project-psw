package com.project.projectaquiler.services;

import com.project.projectaquiler.dto.VehicleRequest;
import com.project.projectaquiler.persistence.entities.VehicleEntity;
import com.project.projectaquiler.persistence.repositories.VehicleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final CloudinaryService cloudinaryService;

    public VehicleEntity saveVehicleEntity(VehicleRequest request, MultipartFile imageFile)
            throws IOException {

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
        return vehicleRepository.save(vehicleEntity);
    }
}
