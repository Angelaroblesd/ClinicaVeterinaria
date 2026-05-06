package com.example.clinica_veterinaria.clinica_veterinaria.DTO;

import java.util.List;

import lombok.Data;

@Data
public class DuenoDTO {
    private Integer id;
    private String nombre;
    private Integer telefono;
    private String direccion;
    private String mail;
    private List<String> mascotas;

}
