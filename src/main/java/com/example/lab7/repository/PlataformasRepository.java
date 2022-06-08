package com.example.lab7.repository;

import com.example.lab7.entity.Plataformas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface PlataformasRepository extends JpaRepository<Plataformas,Integer> {
}
