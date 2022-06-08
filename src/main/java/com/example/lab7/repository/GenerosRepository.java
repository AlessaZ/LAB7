package com.example.lab7.repository;

import com.example.lab7.entity.Generos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GenerosRepository  extends JpaRepository<Generos,Integer> {
}
