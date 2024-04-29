package com.project.projectaquiler.persistence.repositories;

import com.project.projectaquiler.persistence.entities.VehicleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity, String> {
}
