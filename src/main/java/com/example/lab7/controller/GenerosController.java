package com.example.lab7.controller;

import com.example.lab7.entity.Generos;
import com.example.lab7.repository.GenerosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class GenerosController {

    @Autowired
    GenerosRepository generosRepository;

    @GetMapping("/generos")
    public List<Generos> obtenerPlataformas(){
        return generosRepository.findAll();
    }

}
