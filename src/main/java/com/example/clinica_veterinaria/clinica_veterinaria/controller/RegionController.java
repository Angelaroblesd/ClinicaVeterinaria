package com.example.clinica_veterinaria.clinica_veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.RegionDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Region;
import com.example.clinica_veterinaria.clinica_veterinaria.service.RegionService;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping
    public ResponseEntity<List<RegionDTO>> listar() {
        return new ResponseEntity<>(
                regionService.obtenerTodos(),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionDTO> buscar(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                regionService.buscarPorId(id),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegionDTO> guardar(
            @RequestBody RegionDTO dto) {
        return new ResponseEntity<>(
                regionService.guardar(dto),
                HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Region> actualizar(
            @PathVariable Integer id,
            @RequestBody Region region) {
        return new ResponseEntity<>(
                regionService.actualizarRegion(id, region),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(
            @PathVariable Integer id) {
        return new ResponseEntity<>(
                regionService.eliminar(id),
                HttpStatus.OK);
    }
}