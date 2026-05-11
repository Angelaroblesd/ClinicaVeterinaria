package com.example.clinica_veterinaria.clinica_veterinaria.Controller;

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

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.ClinicaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Clinica;
import com.example.clinica_veterinaria.clinica_veterinaria.service.ClinicaService;

@RestController
@RequestMapping("/api/v1/clinicas")
public class ClinicaController {
    @Autowired
    private ClinicaService clinicaService;

    @GetMapping
    public ResponseEntity<List<ClinicaDTO>> todasLasClinicas() {
       List<ClinicaDTO> clinicas = clinicaService.obtenerTodos();
       if (clinicas.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(clinicas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClinicaDTO> buscarPorId(@PathVariable Integer id) {
       try {
           ClinicaDTO clinica = clinicaService.buscarPorId(id);
           return new ResponseEntity<>(clinica, HttpStatus.OK);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<Clinica> agregarClinica(@RequestBody Clinica clinica) {
       try {
           Clinica guardado = clinicaService.guardarClinica(clinica);
           return new ResponseEntity<>(guardado, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Clinica> modificarClinica(@PathVariable Integer id, @RequestBody Clinica clinica){
       try{
            Clinica newClinica = clinicaService.actualizarClinica( id, clinica);
           return new ResponseEntity<>(newClinica, HttpStatus.OK);
       }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarClinica(@PathVariable Integer id) {
       String resultado = clinicaService.eliminar(id);
      
       // Si el mensaje contiene "exitosamente", es un éxito
       if (resultado.contains("exitosamente")) {
           return new ResponseEntity<>(resultado, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
       }
   }



}
