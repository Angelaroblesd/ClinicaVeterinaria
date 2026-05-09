package com.example.clinica_veterinaria.clinica_veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class DuenoDTO {
    private Integer id;
    private String rut;
    private String nombre;
    private String telefono;
    private String direccion;
    private String mail;
    private List<String> mascotas;

}
