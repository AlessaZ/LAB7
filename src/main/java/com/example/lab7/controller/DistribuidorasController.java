package com.example.lab7.controller;

import com.example.lab7.entity.Distribuidoras;
import com.example.lab7.repository.DistribuidorasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class DistribuidorasController {

    @Autowired
    DistribuidorasRepository distribuidorasRepository;

    @GetMapping("/distribuidora")
    public List<Distribuidoras> obtenerDistribuidoras() {
        return distribuidorasRepository.findAll();
    }

    @GetMapping("/distribuidora/{id}")
    public ResponseEntity<HashMap<String, Object>> obtenerDistribuidoraPorId(@PathVariable("id") String idStr) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            Optional<Distribuidoras> optDist = distribuidorasRepository.findById(Integer.parseInt(idStr));
            if (optDist.isPresent()) {
                response.put("result", "success");
                response.put("distribuidora", optDist.get());
                return ResponseEntity.ok(response);
            } else {
                response.put("result", "failure");
                response.put("msg", "Distribuidora no encontrada");
            }
        } catch (NumberFormatException e) {
            response.put("result", "failure");
            response.put("msg", "El ID debe ser un número entero positivo");
        }
        return ResponseEntity.badRequest().body(response);
    }

    @PostMapping("/distribuidora")
    public ResponseEntity<HashMap<String, Object>> guardarDistribuidoras(
            @RequestBody Distribuidoras distribuidora,
            @RequestParam(value = "fetchId", required = false) boolean fetchId) {

        HashMap<String, Object> response = new HashMap<>();

        distribuidorasRepository.save(distribuidora);
        if (fetchId) {
            response.put("id", distribuidora.getIddistribuidora());
        }
        response.put("estado", "creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @PutMapping("/distribuidora")
    public ResponseEntity<HashMap<String, Object>> actualizarDistribuidora(@RequestBody Distribuidoras distribuidora) {

        HashMap<String, Object> response = new HashMap<>();

        if (distribuidora.getIddistribuidora() != null && distribuidora.getIddistribuidora() > 0) {
            if (distribuidorasRepository.existsById(distribuidora.getIddistribuidora())) {
                distribuidorasRepository.save(distribuidora);
                response.put("estado", "actualizado");
                return ResponseEntity.ok(response);
            } else {
                response.put("estado", "error");
                response.put("msg", "La distribuidora a actualizar no existe");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } else {
            response.put("estado", "error");
            response.put("msg", "Debe enviar un ID");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @DeleteMapping("/distribuidora/{id}")
    public ResponseEntity<HashMap<String, Object>> borrarDistribuidora(@PathVariable("id") String idStr) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            int id = Integer.parseInt(idStr);
            if (distribuidorasRepository.existsById(id)) {
                distribuidorasRepository.deleteById(id);
                response.put("estado", "borrado");
                return ResponseEntity.ok(response);
            } else {
                response.put("estado", "error");
                response.put("msg", "No se encontró la distribuidora con ID " + id);
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            }
        } catch (NumberFormatException e) {
            response.put("estado", "error");
            response.put("msg", "El ID debe ser un número");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @ExceptionHandler
    public ResponseEntity<HashMap<String, String>> gestionExcepcion(HttpServletRequest request) {
        HashMap<String, String> response = new HashMap<>();
        if (request.getMethod().equals("POST") || request.getMethod().equals("PUT")) {
            response.put("estado", "error");
            response.put("msg", "Debe enviar una distribuidora");
        }
        return ResponseEntity.badRequest().body(response);
    }


}
