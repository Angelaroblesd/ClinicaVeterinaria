package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Pago;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer>{

}
