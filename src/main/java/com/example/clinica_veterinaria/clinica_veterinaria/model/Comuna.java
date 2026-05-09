package com.example.clinica_veterinaria.clinica_veterinaria.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de la comuna es obligatorio")
    @Size(min = 3, max = 60, message = "El nombre de la comuna debe tener entre 3 y 60 caracteres")
    @Column(nullable = false, length = 60)
    private String nombreComuna;
    
    @ManyToOne
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @OneToMany(mappedBy = "comuna")
    @ToString.Exclude
    private List<Clinica> clinicas;


}
