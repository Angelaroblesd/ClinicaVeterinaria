package com.example.clinica_veterinaria.clinica_veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class ProcedimientoDTO {
    private String nombre;
    private String descripcion;
    private List<String> mascotas;
}
