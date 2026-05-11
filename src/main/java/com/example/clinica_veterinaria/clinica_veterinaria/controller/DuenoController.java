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
    private DuenoService duenoService;

    @GetMapping
    public ResponseEntity<List<DuenoDTO>> listar() {
        return new ResponseEntity<>(duenoService.obtenerTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody DuenoDTO dto){
        try{
            DuenoDTO nuevoDueno = duenoService.guardar(dto);
            return ResponseEntity.ok(nuevoDueno);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Dueno> actualizar(@PathVariable Integer id,
            @RequestBody Dueno dueno) {
        return new ResponseEntity<>(
                duenoService.actualizarDueno(id, dueno),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable Integer id) {
        return new ResponseEntity<>(duenoService.eliminar(id), HttpStatus.OK);
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<DuenoDTO> buscarPorNombre(@PathVariable String nombre) {
        return new ResponseEntity<>(
                duenoService.buscarPorNombre(nombre),
                HttpStatus.OK);
    }

    @GetMapping("/descuento/{id}")
    public ResponseEntity<String> aplicarDescuento(@PathVariable Integer id) {
        return new ResponseEntity<>(
                duenoService.aplicarDescuento(id),
                HttpStatus.OK);
    }
}