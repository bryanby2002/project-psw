package com.project.projectaquiler.persistence.repositories;

import java.util.List;

import com.project.projectaquiler.persistence.entities.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import com.project.projectaquiler.persistence.entities.RoleEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity, String> {

    List<RoleEntity> findByRoleIn(List<RoleEnum> roles);
    
}
