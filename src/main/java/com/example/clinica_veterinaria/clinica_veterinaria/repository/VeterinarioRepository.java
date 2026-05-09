package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Veterinario;

@Repository
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer>{
    List<Veterinario> findByEspecialidad(String especialidad);

}
