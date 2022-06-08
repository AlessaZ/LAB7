package com.example.lab7.repository;

import com.example.lab7.entity.Distribuidoras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DistribuidorasRepository extends JpaRepository<Distribuidoras,Integer> {
}
