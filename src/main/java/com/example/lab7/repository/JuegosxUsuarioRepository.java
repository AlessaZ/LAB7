package com.example.lab7.repository;

import com.example.lab7.entity.JuegosxUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface JuegosxUsuarioRepository extends JpaRepository<JuegosxUsuario, Integer> {    @Transactional
    @Modifying
    @Query(value = "Insert INTO juegosxusuario (idusuario, idjuego, cantidad) VALUES (?,?,1)", nativeQuery = true)
    void registrarJuegoPorUser(int idusuario, int idjuego);
}
