package com.example.clinica_veterinaria.clinica_veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.ComunaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Comuna;
import com.example.clinica_veterinaria.clinica_veterinaria.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    @GetMapping
    public ResponseEntity<List<ComunaDTO>> listar() {
        return new ResponseEntity<>(
                comunaService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComunaDTO> buscar(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                comunaService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ComunaDTO> guardar(
            @RequestBody ComunaDTO dto) {
        return new ResponseEntity<>(
                comunaService.guardar(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comuna> actualizar(
            @PathVariable Integer id,
            @RequestBody Comuna comuna) {
        return new ResponseEntity<>(
                comunaService.actualizarComuna(id, comuna),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                comunaService.eliminar(id),
                HttpStatus.OK);
    }
}