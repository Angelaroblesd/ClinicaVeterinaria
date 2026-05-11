package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Raza;

@Repository
public interface RazaRepository extends JpaRepository<Raza, Integer>{
    Optional<Raza> findByNombreRaza(String nombre);

}
