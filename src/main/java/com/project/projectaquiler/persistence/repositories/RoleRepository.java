package com.project.projectaquiler.persistence.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.project.projectaquiler.persistence.entities.RoleEntity;

public interface RoleRepository extends CrudRepository<RoleEntity, String> {

    List<RoleEntity> findRoleEntitiesByRoleEnumIn(List<String> roleNames);
    
}
