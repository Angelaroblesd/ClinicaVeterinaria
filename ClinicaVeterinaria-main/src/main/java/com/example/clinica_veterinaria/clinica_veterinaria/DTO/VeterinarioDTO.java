package com.example.clinica_veterinaria.clinica_veterinaria.DTO;

import java.util.List;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Consulta;

import lombok.Data;

@Data
public class VeterinarioDTO {
    private String nombreVeterinario;
    private String especialidad;
    private List<Consulta> consultas;

}
