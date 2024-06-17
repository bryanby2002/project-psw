package com.project.projectaquiler.controllers;

import com.project.projectaquiler.persistence.repositories.BoletinRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.project.projectaquiler.persistence.entities.Boletin;

@RestController
@RequestMapping(path = "/boletin")
@AllArgsConstructor
public class BoletinController {

    private final BoletinRepository boletinRepository;

    @PostMapping(path = "/create")
    public ResponseEntity<?> crearBoletin(@RequestBody Boletin boletin){
        return new ResponseEntity<>(boletinRepository.save(boletin), HttpStatus.CREATED);
    }

    @GetMapping(path = "/list")
    public ResponseEntity<?> getAllBoletin(){
        return new ResponseEntity<>(boletinRepository.findAll(), HttpStatus.OK);
    }

}
