package com.example.lab7.controller;


import com.example.lab7.entity.Distribuidoras;
import com.example.lab7.entity.Juegos;
import com.example.lab7.entity.Plataformas;
import com.example.lab7.entity.JuegosUserDto;
import com.example.lab7.repository.JuegosRepository;
import com.sun.source.tree.LabeledStatementTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class JuegosController {

    @Autowired
    JuegosRepository juegosRepository;

    @GetMapping("/juego")
    public List<Juegos> obtenerJuegos() {
        return juegosRepository.findAll();
    }

    @GetMapping("/juego/{id}")
    public ResponseEntity<HashMap<String,Object>> obtenerJuegoPorId(@PathVariable("id") String idStr) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Optional<Juegos> optionalJuegos = juegosRepository.findById(Integer.parseInt(idStr));
            if (optionalJuegos.isPresent()) {
                response.put("result", "success");
                response.put("juego", optionalJuegos.get());
                return ResponseEntity.ok(response);
            } else {
                response.put("result", "failure");
                response.put("msg", "Juego no encontrado.");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.put("result", "failure");
            response.put("msg", "El ID debe ser un número entero positivo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> guardarJuegos(
            @RequestBody Juegos juego,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> response = new HashMap<>();

        juegosRepository.save(juego);
        if (fetchId) {
            response.put("id", juego.getIdjuego());
        }
        response.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }


    @GetMapping(value="/juegos/usuario/{id}")
    public List<JuegosUserDto> listarJuegosUser(@PathVariable("id") String idStr){
        int idInt;
        try {
            idInt = Integer.parseInt(idStr);
            return juegosRepository.obtenerJuegosPorUser(idInt);
        }catch (NumberFormatException e){
            return new ArrayList<>();
        }
    }

    @GetMapping(value = "/juegos/nocompra/{id}")
    public  List<Juegos> listarJuegosNoComprados(@PathVariable("id") String idStr){
        int idInt;
        try {
            idInt = Integer.parseInt(idStr);
            return juegosRepository.obtenerJuegosNoCompradosPorUser(idInt);
        }catch (NumberFormatException e){
            return new ArrayList<>();
        }
    }

    @PutMapping("/juego")
    public ResponseEntity<HashMap<String, Object>> actualizarJuego(@RequestBody Juegos juego) {

        HashMap<String, Object> response = new HashMap<>();

        if (juego.getIdjuego() != null && juego.getIdjuego() > 0) {
            if (juegosRepository.existsById(juego.getIdjuego())) {
                juegosRepository.save(juego);
                response.put("estado", "Juego actualizado");
                return ResponseEntity.ok(response);
            } else {
                response.put("estado", "error");
                response.put("msg", "El juego a actualizar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } else {
            response.put("estado", "error");
            response.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping(value = "/juego/{id}")
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
