package com.example.clinica_veterinaria.clinica_veterinaria.DTO;

import lombok.Data;

@Data
public class MascotaDTO {
    private Integer id;
    private String nombre;
    private Integer edad;
    private String sexo;
    private String raza;
    private String especie;
    private String dueno;

}
