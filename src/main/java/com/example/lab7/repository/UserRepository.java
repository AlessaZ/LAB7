package com.example.lab7.repository;

import com.example.lab7.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByCorreo(String correo);
}
