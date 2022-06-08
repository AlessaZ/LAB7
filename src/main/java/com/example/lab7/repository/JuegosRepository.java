package com.example.lab7.repository;

import com.example.lab7.entity.Juegos;
import com.example.lab7.entity.JuegosUserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JuegosRepository extends JpaRepository<Juegos,Integer> {
    @Query(value = "Select  j.idjuego, j.nombre, j.descripcion, g.nombre as genero, j.image as imageURL from juegos j " +
            "inner join juegosxusuario ju  on j.idjuego=ju.idjuego " +
            "inner join usuarios u on ju.idusuario=u.idusuario " +
            "inner join generos g on g.idgenero=j.idgenero Where u.idusuario= ?",nativeQuery = true)
    List<JuegosUserDto> obtenerJuegosPorUser(int idusuario);
}
