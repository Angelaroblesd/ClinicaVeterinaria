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

public class Clinica {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @NotBlank(message = "El nombre de la clinica es obligatorio")
     @Size(min = 3, max = 60, message = "El nombre de la clinica debe tener entre 3 y 60 caracteres")
     @Column(nullable = false, length = 60, unique = true)
     private String nombreClinica;

     @NotBlank(message = "la direccion de la clinica es obligatoria")
     @Size(min = 5, max = 200, message = "su direccion debe tener minimo 5 caracteres  y 200 maximo")
     @Column(nullable = false, length = 200)
     private String direccion;

     @NotBlank(message = "el telefono de la clinica es obigatorio")
     @Size(min = 9, max = 12, message = "su telefono debe tener minimo 9 numeros")
     @Column(nullable = false, length = 12)
     private String telefono;

     @ManyToOne
     @JoinColumn(name = "comuna_id", nullable = false)
     private Comuna comuna;

     @OneToMany(mappedBy = "clinica")
     @ToString.Exclude
     private List<Veterinario> veterinarios;

}
