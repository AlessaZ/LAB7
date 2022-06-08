package com.example.lab7.controller;

import com.example.lab7.entity.Paises;
import com.example.lab7.repository.PaisesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PaisesController {

    @Autowired
    PaisesRepository paisesRepository;

    @GetMapping("/pais")
    public List<Paises> obtenerPaises() {
        return paisesRepository.findAll();
    }

}
