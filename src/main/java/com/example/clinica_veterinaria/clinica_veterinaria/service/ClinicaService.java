package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clinica_veterinaria.clinica_veterinaria.DTO.ClinicaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Clinica;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.ClinicaRepository;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClinicaService {
    @Autowired
    private ClinicaRepository clinicaRepository;
    

    public List<ClinicaDTO> obtenerTodos() {
       return clinicaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }


    public ClinicaDTO buscarPorId(Integer id) {
       Clinica clinica = clinicaRepository.findById(id)
           .orElseThrow(() -> new RuntimeException("clinica no encontrada"));
       return convertirADTO(clinica);
    }

    
    public String eliminar(Integer id) {
     try {
           Clinica clinica = clinicaRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! la clinica con ID " + id + " no existe."));
           clinicaRepository.delete(clinica);
           return "la clinica '" + clinica.getNombreClinica() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public Clinica guardarClinica(Clinica clinica) {
       return clinicaRepository.save(clinica);
    }

    public Clinica actualizarClinica(Integer id,Clinica cli){
       Clinica clinica = clinicaRepository.findById(id).orElseThrow(() -> new RuntimeException("la clinica no existe"));
       if(cli.getNombreClinica() != null){
           clinica.setNombreClinica(cli.getNombreClinica());
       }
       if(cli.getDireccion() != null){
           clinica.setDireccion(cli.getDireccion());
       }
       if(cli.getTelefono() != null){
           clinica.setTelefono(cli.getTelefono());
       }
       if(cli.getComuna() != null){
          clinica.setComuna(cli.getComuna());
       }
       return clinicaRepository.save(clinica);
     }

    private ClinicaDTO convertirADTO(Clinica clinica) {
       ClinicaDTO dto = new ClinicaDTO();
       dto.setId(clinica.getId());
       dto.setNombreClinica(clinica.getNombreClinica());
       dto.setDireccion(clinica.getDireccion());
       dto.setTelefono(clinica.getTelefono());

       if (clinica.getComuna() != null) {
         dto.setNombreComuna(clinica.getComuna().getNombreComuna());
         dto.setNombreRegion(clinica.getComuna().getRegion().getNombreRegion());
       }
              return dto;
     }

}
