package com.example.clinica_veterinaria.clinica_veterinaria.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Consulta {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Integer id;

     @NotNull(message = "La fecha es obligatoria")
     @Column(nullable = false)
     private LocalDate fecha;

     @NotBlank(message = "El motivo es obligatorio")
     @Size(min = 5, max = 255, message = "El motivo debe tener entre 5 y 255 caracteres")
     @Column(nullable = false, length = 255)
     private String motivo;

     @NotBlank(message = "El diagnostico es obligatorio")
     @Size(min = 5, max = 500, message = "El motivo debe tener entre 5 y 500 caracteres")
     @Column(nullable = false, length = 500)
     private String diagnostico;

     @ManyToOne
     @JoinColumn(name = "mascota_id", nullable = false)
     private Mascota mascota;

     @ManyToOne
     @JoinColumn(name = "veterinario_id", nullable = false)
     private Veterinario veterinario; 
     
     @ManyToMany
     @JoinTable( name = "consulta_procedimiento", joinColumns = @JoinColumn(name = "consulta_id"), 
     inverseJoinColumns = @JoinColumn(name = "procedimiento_id"))
     private List<Procedimiento> procedimientos;

     @OneToMany(mappedBy = "consulta")
     @ToString.Exclude
     private List<Pago> pagos;
}
