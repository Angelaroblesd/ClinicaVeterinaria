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
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Veterinario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de el veterinario es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nombreVeterinario;

    @NotBlank(message = "el telefono es obigatorio")
    @Size(min = 9, max = 12, message = "su telefono debe tener minimo 9 numeros")
    @Column(nullable = false, length = 12)
    private String telefono;

    @NotBlank(message = "la especialidad es obligatoria")
    @Size(min = 3, max = 100, message = "su especialidad debe tener min 3 y max 100 caracteres")
    @Column(nullable = false, length = 100)
    private String especialidad;

    @ManyToOne
    @JoinColumn(name = "clinica_id", nullable = false)
    private Clinica clinica;

    @OneToMany(mappedBy = "veterinario")
    @ToString.Exclude
    private List<Consulta> consultas;

}
