package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Veterinario;
public interface VeterinarioRepository extends JpaRepository<Veterinario, Integer>{
       List<Veterinario> findByEspecialidad(String especialidad);

}
