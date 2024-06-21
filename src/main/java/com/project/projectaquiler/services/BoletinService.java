package com.project.projectaquiler.services;

import com.project.projectaquiler.persistence.entities.Boletin;
import com.project.projectaquiler.persistence.repositories.BoletinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class BoletinService {

    private final BoletinRepository boletinRepository;

    public Boletin save(Boletin boletin) {
        return boletinRepository.save(boletin);
    }


}
