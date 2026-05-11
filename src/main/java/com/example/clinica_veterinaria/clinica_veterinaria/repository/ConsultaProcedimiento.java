package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaProcedimiento extends JpaRepository<ConsultaProcedimiento, Integer>{

}
