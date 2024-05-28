package com.project.projectaquiler.persistence.repositories;

import com.project.projectaquiler.persistence.entities.VehicleEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends CrudRepository<VehicleEntity, String> {

    @Query(
            "select v from VehicleEntity v where v.brand like :param% " +
                    "or v.model like :param% " +
                    "or v.typeVehicle like :param " +
                    "or v.color like :param%"
    )
    List<VehicleEntity> seachByParam(@Param(value="param") String param);
}
