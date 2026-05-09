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
public class Dueno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El rut es obligatorio")
    @Size(min = 9, max = 12, message = "El rut debe tener entre 9 y 12 caracteres")
    @Column(nullable = false, length = 12)
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 60, message = "El nombre debe tener entre 3 y 60 caracteres")
    @Column(nullable = false, length = 60)
    private String nombre;

    @NotBlank(message = "el telefono es obigatorio")
    @Size(min = 9, max = 12, message = "su telefono debe tener minimo 9 numeros")
    @Column(nullable = false, length = 12)
    private String telefono;

    @NotBlank(message = "la direccion es obligatoria")
    @Size(min = 5, max = 80, message = "su direccion debe tener minimo 5 caracteres  y 80 maximo")
    @Column(nullable = false, length = 80)
    private String direccion;

    @NotBlank(message = "el mail es obligatorio")
    @Size(min = 10, max = 80, message = "su mail debe tener minimo 10 caracteres y 80 maximo")
    @Column(nullable = false, length = 80)
    private String mail;

    @OneToMany(mappedBy = "dueno")
    @ToString.Exclude
    private List<Mascota> mascotas;


}
