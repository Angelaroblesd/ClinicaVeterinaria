package com.example.clinica_veterinaria.clinica_veterinaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Pago;
public interface PagoRepository extends JpaRepository<Pago, Integer>{

      @Query("SELECT SUM(p.monto) FROM Pago p WHERE p.consulta.veterinario.clinica.id = :clinicaId")
      Integer totalRecaudadoPorClinica(@Param("clinicaId") Integer clinicaId);

}
