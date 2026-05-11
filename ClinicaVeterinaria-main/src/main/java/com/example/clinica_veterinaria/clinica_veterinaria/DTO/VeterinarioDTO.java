package com.example.clinica_veterinaria.clinica_veterinaria.DTO;

import lombok.Data;

@Data
public class VeterinarioDTO {
    private Integer id;
    private String nombre;
    private String telefono;
    private String especialidad;
    private Integer totalConsultas;

}
