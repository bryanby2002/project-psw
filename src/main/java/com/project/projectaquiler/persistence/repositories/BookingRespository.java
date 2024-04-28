package com.project.projectaquiler.persistence.repositories;

import com.project.projectaquiler.persistence.entities.BookingEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRespository extends CrudRepository<BookingEntity, String> {
}
