package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Consulta;

@Repository
public interface ConsultaRepository extends  JpaRepository<Consulta, Integer>{
    List<Consulta> findByVeterinarioIdAndFecha(Integer veterinarioId,LocalDate fecha);

}
