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

import com.example.clinica_veterinaria.clinica_veterinaria.model.Especie;
import com.example.clinica_veterinaria.clinica_veterinaria.service.EspecieService;

@RestController
@RequestMapping("/api/v1/especies")
public class EspecieController {

    @Autowired
    private EspecieService especieService;

    @GetMapping
    public ResponseEntity<List<Especie>> listar() {
        return new ResponseEntity<>(
                especieService.obtenerTodos(),
                HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Especie> guardar(@RequestBody Especie especie) {
        return new ResponseEntity<>(
                especieService.guardar(especie),
                HttpStatus.CREATED);
    }
}