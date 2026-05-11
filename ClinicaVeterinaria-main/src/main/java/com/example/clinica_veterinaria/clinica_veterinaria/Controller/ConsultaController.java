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
import com.example.clinica_veterinaria.clinica_veterinaria.DTO.ConsultaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Consulta;
import com.example.clinica_veterinaria.clinica_veterinaria.service.ConsultaService;

@RestController
@RequestMapping("/api/v1/consultas")
public class ConsultaController {
    @Autowired
    private ConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> todasLasConsultas() {
       List<ConsultaDTO> consultas = consultaService.obtenerTodos();
       if (consultas.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(consultas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> buscarPorId(@PathVariable Integer id) {
       try {
           ConsultaDTO consulta = consultaService.buscarPorId(id);
           return new ResponseEntity<>(consulta, HttpStatus.OK);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<Consulta> agregarConsulta(@RequestBody Consulta consulta) {
       try {
           Consulta guardado = consultaService.guardarConsulta(consulta);
           return new ResponseEntity<>(guardado, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> modificarConsulta(@PathVariable Integer id, @RequestBody Consulta consulta){
       try{
            Consulta newConsulta = consultaService.actualizarConsulta( id, consulta);
           return new ResponseEntity<>(newConsulta, HttpStatus.OK);
       }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarConsulta(@PathVariable Integer id) {
       String resultado = consultaService.eliminar(id);
       if (resultado.contains("exitosamente")) {
           return new ResponseEntity<>(resultado, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
       }
    }



}
