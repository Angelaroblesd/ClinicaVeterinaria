package com.example.clinica_veterinaria.clinica_veterinaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
  
    @NotBlank(message = "El nombre de la region es obligatorio")
    @Size(min = 2, max = 40, message = "debe tener entre 2 y 40 caracteres")
    @Column(nullable = false, length = 40)
    private String nombreRegion;

    @OneToMany(mappedBy = "region")
    @ToString.Exclude
    private List<Comuna> comunas;

}
