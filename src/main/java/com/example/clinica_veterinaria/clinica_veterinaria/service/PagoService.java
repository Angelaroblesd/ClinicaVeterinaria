package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clinica_veterinaria.clinica_veterinaria.DTO.PagoDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Dueno;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Pago;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.DuenoRepository;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.PagoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PagoService {
    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private DuenoRepository duenoRepository;

     
    private PagoDTO convertirADTO(Pago pago) {
       PagoDTO dto = new PagoDTO();
       dto.setId(pago.getId());
       dto.setFecha(pago.getFecha());
       dto.setMonto(pago.getMonto());
       dto.setMetodoPago(pago.getMetodoPago());
       if (pago.getConsulta() != null) {
         dto.setConsultaId(pago.getConsulta().getId());
       }
              return dto;
    }

    public List<PagoDTO> obtenerTodos() {
       return pagoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public PagoDTO buscarPorId(Integer id) {
       Pago pago = pagoRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("el pago no existe"));
       return convertirADTO(pago);
    }

    public String eliminar(Integer id) {
     try {
           Pago pago = pagoRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! el pago con ID " + id + " no existe."));
           pagoRepository.delete(pago);
           return "el pago '" + pago.getId() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public Pago guardarPago(Pago pago, Integer duenoId) {
    Dueno dueno = duenoRepository.findById(duenoId) .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
    int cantidadMascotas = dueno.getMascotas().size();
    int descuento = 0;
    // Descuento del 10% si tiene 5 o más mascotas
    if (cantidadMascotas >= 5) {
        descuento = pago.getMonto() * 10 / 100;
    }
    int montoFinal = pago.getMonto() - descuento;
    pago.setMonto(montoFinal);
    return pagoRepository.save(pago);
}

    public Pago actualizarPago(Integer id,Pago pa){
       Pago pago = pagoRepository.findById(id).orElseThrow(() -> new RuntimeException("el pago no existe"));
       if(pa.getFecha() != null){
           pago.setFecha(pa.getFecha());
       }
       if(pa.getMonto() != null){
           pago.setMonto(pa.getMonto());
       }
       if(pa.getMetodoPago() != null){
           pago.setMetodoPago(pa.getMetodoPago());
       }
       if(pa.getConsulta() != null){
          pago.setConsulta(pa.getConsulta());
       }
       return pagoRepository.save(pago);
    }

    public Integer totalRecaudadoPorClinica(Integer clinicaId) {
    return pagoRepository.totalRecaudadoPorClinica(clinicaId);
    }

    
}
