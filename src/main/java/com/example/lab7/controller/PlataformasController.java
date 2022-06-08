package com.example.lab7.controller;

import com.example.lab7.entity.Plataformas;
import com.example.lab7.repository.PlataformasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class PlataformasController {

    @Autowired
    PlataformasRepository plataformasRepository;

    @GetMapping("/plataforma")
    public List<Plataformas> obtenerPlataformas(){
        return plataformasRepository.findAll();
    }

}
