package com.example.lab7.controller;


import com.example.lab7.entity.Distribuidoras;
import com.example.lab7.entity.Juegos;
import com.example.lab7.repository.JuegosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/juegos")
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @GetMapping("/lista/all")
    public List<Juegos> obtenerTodosLosJuegos() {
        return juegosRepository.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> guardarJuegos(@RequestBody Juegos juego) {
        HashMap<String, Object> response = new HashMap<>();
        Juegos juegos=juegosRepository.save(juego);
        response.put("estado", "Creación exitosa");
        response.put("juego", juegos);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<HashMap<String,Object>> eliminarJuegos(@PathVariable("id") String idStr){

        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            if (juegosRepository.existsById(id)) {
                juegosRepository.deleteById(id);
                responseMap.put("estado", "borrado exitoso");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "No se encontró el juego con el id: " + id);
                return ResponseEntity.badRequest().body(responseMap);
            }
        } catch (NumberFormatException ex) {
            responseMap.put("estado", "error");
            responseMap.put("msg", "El ID debe ser un número.");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }




}
