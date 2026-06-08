package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.ComunaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Comuna;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.ComunaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ComunaService {

    @Autowired
    private ComunaRepository comunaRepository;

    public List<ComunaDTO> obtenerTodos() {

        return comunaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public ComunaDTO buscarPorId(Integer id) {
        Comuna comuna = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("comuna no encontrada"));

      return convertirADTO(comuna);
    }

    public ComunaDTO guardar(ComunaDTO dto) {
        Comuna comuna = convertirAEntidad(dto);
        return convertirADTO(
                comunaRepository.save(comuna));
    }

    public Comuna actualizarComuna(Integer id, Comuna comuna) {
        Comuna com = comunaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("la comuna no existe"));
        com.setNombreComuna(comuna.getNombreComuna());

      return comunaRepository.save(com);
    }

    public String eliminar(Integer id) {
        if(comunaRepository.existsById(id)) {
            comunaRepository.deleteById(id);
            return "Comuna eliminada exitosamente";
        }
        return "Comuna no encontrada";
    }

    private ComunaDTO convertirADTO(Comuna comuna) {
        ComunaDTO dto = new ComunaDTO();
        dto.setId(comuna.getId());
        dto.setNombreComuna(comuna.getNombreComuna());
        if(comuna.getRegion() != null){
        dto.setRegion(comuna.getRegion().getNombreRegion());
        }

        return dto;
    }

    private Comuna convertirAEntidad(ComunaDTO dto) {
        Comuna comuna = new Comuna();
        comuna.setId(dto.getId());
        comuna.setNombreComuna(dto.getNombreComuna());
        return comuna;
    }
}