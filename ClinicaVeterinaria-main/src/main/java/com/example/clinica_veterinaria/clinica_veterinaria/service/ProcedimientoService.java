package com.example.clinica_veterinaria.clinica_veterinaria.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Procedimiento;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.ProcedimientoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProcedimientoService {
    @Autowired
    private ProcedimientoRepository procedimientoRepository;

    public List<Procedimiento> obtenerTodos() {
       return procedimientoRepository.findAll();
    }
   
    public Procedimiento buscarPorId(Integer id) {
       Procedimiento pro = procedimientoRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("el procedimiento no existe"));
       return pro;
    }

    public String eliminar(Integer id) {
     try {
           Procedimiento pro = procedimientoRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! el procedimiento con ID " + id + " no existe."));
           procedimientoRepository.delete(pro);
           return "el procedimiento '" + pro.getId() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public Procedimiento guardarProcedimiento(Procedimiento pro) {
       return procedimientoRepository.save(pro);
    }


    public Procedimiento actualizarProcedimiento(Integer id,Procedimiento pro){
       Procedimiento procedimiento = procedimientoRepository.findById(id).orElseThrow(() -> new RuntimeException("el procedimiento no existe"));
       if(pro.getNombre() != null){
           procedimiento.setNombre(pro.getNombre());
       }
       if(pro.getDescripcion() != null){
           procedimiento.setDescripcion(pro.getDescripcion());
       }
       return procedimientoRepository.save(procedimiento);
    }

}




