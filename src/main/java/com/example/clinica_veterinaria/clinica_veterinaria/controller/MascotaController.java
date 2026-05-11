package com.example.clinica_veterinaria.clinica_veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.MascotaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Mascota;
import com.example.clinica_veterinaria.clinica_veterinaria.service.MascotaService;

@RestController
@RequestMapping("/api/v1/mascotas")
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> listar() {
        return new ResponseEntity<>(mascotaService.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> buscar(@PathVariable Integer id) {
        return new ResponseEntity<>(mascotaService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody MascotaDTO dto) {

        try {
            MascotaDTO nuevaMascota = mascotaService.guardar(dto);
            return ResponseEntity.ok(nuevaMascota);
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mascota> actualizar(@PathVariable Integer id,
            @RequestBody Mascota mascota) {
        return new ResponseEntity<>(
                mascotaService.actualizarMascota(id, mascota),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        return new ResponseEntity<>(mascotaService.eliminar(id), HttpStatus.OK);
    }
}