package com.example.lab7.controller;

import com.example.lab7.entity.Distribuidoras;
import com.example.lab7.repository.DistribuidorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/distribuidoras")
public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @PostMapping("/save")
    public ResponseEntity<HashMap<String, Object>> guardarDistribuidoras(@RequestBody Distribuidoras distribuidora) {
        HashMap<String, Object> response = new HashMap<>();

        Distribuidoras distCreated = distribuidorasRepository.save(distribuidora);
        response.put("estado", "Creación exitosa");
        response.put("distribuidora", distCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/lista/all")
    public List<Distribuidoras> obtenerDistribuidoras() {
        return distribuidorasRepository.findAll();
    }

    @GetMapping("/all/ordenNombre")
    public List<Distribuidoras> listarDistribuidorasbyNombre() {
        return distribuidorasRepository.findAll(Sort.by("nombre"));
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<HashMap<String, Object>> obtenerDistribuidoraId(@PathVariable("id") String idStr) {
        HashMap<String, Object> responseJson = new HashMap<>();
        try {
            Optional<Distribuidoras> optDist = distribuidorasRepository.findById(Integer.parseInt(idStr));
            if (optDist.isPresent()) {
                responseJson.put("result", "success");
                responseJson.put("product", optDist.get());
                return ResponseEntity.ok(responseJson);
            } else {
                responseJson.put("msg", "Distribuidora no encontrada");
            }
        } catch (NumberFormatException e) {
            responseJson.put("msg", "El ID debe ser un número entero positivo");
        }
        responseJson.put("result", "failure");
        return ResponseEntity.badRequest().body(responseJson);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarDistribuidora(@PathVariable("id") String idStr) {
        HashMap<String, Object> responseMap = new HashMap<>();

        try {
            int id = Integer.parseInt(idStr);
            if (distribuidorasRepository.existsById(id)) {
                distribuidorasRepository.deleteById(id);
                responseMap.put("estado", "borrado exitoso");
                return ResponseEntity.ok(responseMap);
            } else {
                responseMap.put("estado", "error");
                responseMap.put("msg", "No se encontró la distribuidora con id: " + id);
                return ResponseEntity.badRequest().body(responseMap);
            }
        } catch (NumberFormatException e) {
            responseMap.put("estado", "borrado exitoso");
            responseMap.put("msg", "El ID debe ser un número");
            return ResponseEntity.badRequest().body(responseMap);
        }
    }
}
