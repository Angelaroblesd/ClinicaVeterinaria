package com.example.clinica_veterinaria.clinica_veterinaria.model;

import java.util.List;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Procedimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de el procedimiento es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombre;
    
    @NotBlank(message = "la descripcion es obligatoria")
    @Size(min = 3, max = 500, message = "El nombre debe tener entre 3 y 500 caracteres")
    @Column(nullable = false, length = 500)
    private String descripcion;

    @ManyToMany(mappedBy = "procedimientos")
    @ToString.Exclude
    private List<Consulta> consultas;
}
