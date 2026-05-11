package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Especie;

@Repository
public interface EspecieRepository extends JpaRepository<Especie, Integer>{
    Optional<Especie> findByNombreEspecie(String nombre);

}
