package com.example.clinica_veterinaria.clinica_veterinaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.VeterinarioDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Veterinario;
import com.example.clinica_veterinaria.clinica_veterinaria.service.VeterinarioService;

@RestController
@RequestMapping("/api/v1/veterinarios")
public class VeterinarioController {
    @Autowired
    private VeterinarioService veterinarioService;

    @GetMapping
    public ResponseEntity<List<VeterinarioDTO>> todosLosVeterinarios() {
       List<VeterinarioDTO> veterinarios = veterinarioService.obtenerTodos();
       if (veterinarios.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(veterinarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeterinarioDTO> buscarPorId(@PathVariable Integer id) {
       try {
           VeterinarioDTO veterinario = veterinarioService.buscarPorId(id);
           return new ResponseEntity<>(veterinario, HttpStatus.OK);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<Veterinario> agregarVeterinario(@RequestBody Veterinario veterinario) {
       try {
           Veterinario guardado = veterinarioService.guardarVeterinario(veterinario);
           return new ResponseEntity<>(guardado, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Veterinario> modificarVeterinario(@PathVariable Integer id, @RequestBody Veterinario veterinario){
       try{
            Veterinario newVeterinario = veterinarioService.actualizarVeterinario( id, veterinario);
           return new ResponseEntity<>(newVeterinario, HttpStatus.OK);
       }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarVeterinario(@PathVariable Integer id) {
       String resultado = veterinarioService.eliminar(id);
       if (resultado.contains("exitosamente")) {
           return new ResponseEntity<>(resultado, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
       }
   }

    @GetMapping("/especialidad/{especialidad}")
    public ResponseEntity<List<VeterinarioDTO>> buscarPorEspecialidad(@PathVariable String especialidad) {
           List<VeterinarioDTO> veterinarios = veterinarioService.buscarPorEspecialidad(especialidad);
           if (veterinarios.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
           }
            return new ResponseEntity<>(veterinarios, HttpStatus.OK);       
    }


}
