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

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.PagoDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Pago;
import com.example.clinica_veterinaria.clinica_veterinaria.service.PagoService;

@RestController
@RequestMapping("/api/v1/pagos")
public class PagoController {
    @Autowired
    private  PagoService pagoService;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> todosLosPagos() {
       List<PagoDTO> pagos = pagoService.obtenerTodos();
       if (pagos.isEmpty()) {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }
       return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> buscarPorId(@PathVariable Integer id) {
       try {
           PagoDTO pago = pagoService.buscarPorId(id);
           return new ResponseEntity<>(pago, HttpStatus.OK);
       } catch (RuntimeException e) {
           return ResponseEntity.notFound().build();
       }
    }

    @PostMapping
    public ResponseEntity<Pago> agregarPago(@RequestBody Pago pago,@PathVariable Integer duenoId) {
       try {
           Pago guardado = pagoService.guardarPago(pago,duenoId);
           return new ResponseEntity<>(guardado, HttpStatus.CREATED);
       } catch (Exception e) {
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pago> modificarPago(@PathVariable Integer id, @RequestBody Pago pago){
       try{
            Pago newPago = pagoService.actualizarPago( id, pago);
           return new ResponseEntity<>(newPago, HttpStatus.OK);
       }catch (RuntimeException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarPago(@PathVariable Integer id) {
       String resultado = pagoService.eliminar(id);
       if (resultado.contains("exitosamente")) {
           return new ResponseEntity<>(resultado, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(resultado, HttpStatus.NOT_FOUND);
       }
    }

    @GetMapping("/total-recaudado/{clinicaId}")
    public ResponseEntity<Integer> totalRecaudadoPorClinica(
        @PathVariable Integer clinicaId) {
    Integer total = pagoService.totalRecaudadoPorClinica(clinicaId);
    return ResponseEntity.ok(total);
    }


}
