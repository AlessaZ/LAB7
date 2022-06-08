package com.example.lab7.controller;


import com.example.lab7.entity.Juegos;
import com.example.lab7.repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @GetMapping("/lista/all")
    public List<Juegos> obtenerTodosLosJuegos() {
        return juegosRepository.findAll();
    }


}
