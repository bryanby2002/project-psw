package com.project.projectaquiler.persistence.repositories;

import com.project.projectaquiler.persistence.entities.PermissionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<PermissionEntity, String>{
    
}
