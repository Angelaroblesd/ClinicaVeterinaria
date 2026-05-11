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

import com.example.clinica_veterinaria.clinica_veterinaria.model.Procedimiento;
import com.example.clinica_veterinaria.clinica_veterinaria.service.ProcedimientoService;

@RestController
@RequestMapping("/api/v1/procedimientos")
public class ProcedimientoController {
    @Autowired
    private ProcedimientoService procedimientoService;

    @GetMapping
    public ResponseEntity<List<Procedimiento>> todosLosProcedimientos() {
       List<Procedimiento> procedimientos = procedimientoService.obtenerTodos();
       if (procedimientos.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(procedimientos, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Procedimiento> buscarPorId(@PathVariable Integer id) {
       try {
           Procedimiento procedimiento = procedimientoService.buscarPorId(id);
           return new ResponseEntity<>(procedimiento, HttpStatus.OK);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<Procedimiento> agregarProcedimiento(@RequestBody Procedimiento pro) {
       try {
           Procedimiento guardado = procedimientoService.guardarProcedimiento(pro);
           return new ResponseEntity<>(guardado, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Procedimiento> modificarPago(@PathVariable Integer id, @RequestBody Procedimiento pro){
       try{
            Procedimiento newProcedimiento = procedimientoService.actualizarProcedimiento( id, pro);
           return new ResponseEntity<>(newProcedimiento, HttpStatus.OK);
       }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Integer id) {
       String resultado = procedimientoService.eliminar(id);
       if (resultado.contains("exitosamente")) {
           return new ResponseEntity<>(resultado, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
       }
    }

}
