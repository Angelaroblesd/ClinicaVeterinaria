package com.example.clinica_veterinaria.clinica_veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.DuenoDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Dueno;
import com.example.clinica_veterinaria.clinica_veterinaria.service.DuenoService;

@RestController
@RequestMapping("/api/v1/duenos")
public class DuenoController {

    @Autowired
    private DuenoService dueñoService;

    @GetMapping
    public ResponseEntity<List<DuenoDTO>> listar() {
        return new ResponseEntity<>(dueñoService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DuenoDTO> guardar(@RequestBody DuenoDTO dto) {
        return new ResponseEntity<>(dueñoService.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dueno> actualizar(@PathVariable Integer id,
            @RequestBody Dueno dueno) {

        return new ResponseEntity<>(
                dueñoService.actualizarDueno(id, dueno),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        return new ResponseEntity<>(dueñoService.eliminar(id), HttpStatus.OK);
    }
}