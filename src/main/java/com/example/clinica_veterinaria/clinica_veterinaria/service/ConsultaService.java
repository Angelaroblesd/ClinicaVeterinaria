package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clinica_veterinaria.clinica_veterinaria.DTO.ConsultaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Consulta;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.ConsultaRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    private ConsultaDTO convertirADTO(Consulta consulta) {
       ConsultaDTO dto = new ConsultaDTO();
       dto.setId(consulta.getId());
       dto.setFecha(consulta.getFecha());
       dto.setMotivo(consulta.getMotivo());
       dto.setDiagnostico(consulta.getDiagnostico());
       dto.setNombreMascota(consulta.getMascota().getNombre());
       dto.setSexo(consulta.getMascota().getSexo());
       dto.setEdad(consulta.getMascota().getEdad());
       dto.setNombreVet(consulta.getVeterinario().getNombreVeterinario());
       dto.setEspecialidadVet(consulta.getVeterinario().getEspecialidad());
              return dto;
    }

    public List<ConsultaDTO> obtenerTodos() {
       return consultaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public ConsultaDTO buscarPorId(Integer id) {
       Consulta consulta = consultaRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("la consulta no existe"));
       return convertirADTO(consulta);
    }

    public String eliminar(Integer id) {
     try {
           Consulta consulta = consultaRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! la consulta con ID " + id + " no existe."));
           consultaRepository.delete(consulta);
           return "la consulta '" + consulta.getId() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public Consulta guardarConsulta(Consulta consulta) {
       return consultaRepository.save(consulta);
    }

    public Consulta actualizarConsulta(Integer id,Consulta con){
       Consulta consulta = consultaRepository.findById(id).orElseThrow(() -> new RuntimeException("la consulta no existe"));
       if(con.getFecha() != null){
           consulta.setFecha(con.getFecha());
       }
       if(con.getMotivo() != null){
           consulta.setMotivo(con.getMotivo());
       }
       if(con.getDiagnostico() != null){
           consulta.setDiagnostico(con.getDiagnostico());
       }
       if(con.getMascota() != null){
          consulta.setMascota(con.getMascota());
       }
       if(con.getVeterinario() != null){
          consulta.setVeterinario(con.getVeterinario());
       }
       return consultaRepository.save(consulta);
    }









}
