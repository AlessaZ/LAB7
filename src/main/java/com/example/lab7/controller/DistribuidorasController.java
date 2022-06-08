package com.example.lab7.controller;

import com.example.lab7.entity.Distribuidoras;
import com.example.lab7.repository.DistribuidorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/distribuidoras")
public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> guardarDistribuidoras(@RequestBody Distribuidoras distribuidora) {
        HashMap<String, Object> response = new HashMap<>();

        distribuidorasRepository.save(distribuidora);
        response.put("estado", "Creación exitosa");
        response.put("distribuidora", distribuidora);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/lista/all")
    public List<Distribuidoras> obtenerDistribuidoras() {
        return distribuidorasRepository.findAll();
    }

}
