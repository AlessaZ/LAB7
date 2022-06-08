package com.example.lab7.repository;

import com.example.lab7.entity.Paises;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PaisesRepository extends JpaRepository<Paises,Integer> {
}
