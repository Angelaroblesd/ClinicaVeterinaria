package com.example.clinica_veterinaria.clinica_veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Raza;
import com.example.clinica_veterinaria.clinica_veterinaria.service.RazaService;

@RestController
@RequestMapping("/api/v1/razas")
public class RazaController {

    @Autowired
    private RazaService razaService;

    @GetMapping
    public ResponseEntity<List<Raza>> listar() {
        return new ResponseEntity<>(
                razaService.obtenerTodos(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Raza> guardar(@RequestBody Raza raza) {
        return new ResponseEntity<>(
                razaService.guardar(raza),
                HttpStatus.CREATED);
    }
}