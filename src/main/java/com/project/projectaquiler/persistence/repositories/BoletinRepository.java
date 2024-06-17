package com.project.projectaquiler.persistence.repositories;

import com.project.projectaquiler.persistence.entities.Boletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletinRepository extends JpaRepository<Boletin, String> {
}
