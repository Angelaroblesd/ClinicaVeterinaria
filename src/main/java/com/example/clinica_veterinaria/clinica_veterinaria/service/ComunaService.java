package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;
import java.util.Optional;
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
        Optional<Comuna> comunaBuscada = comunaRepository.findById(id);
        if(comunaBuscada.isPresent()) {
            Comuna comuna = comunaBuscada.get();
            return convertirADTO(comuna);
        }
        return null;
    }

    public ComunaDTO guardar(ComunaDTO dto) {
        Comuna comuna = convertirAEntidad(dto);
        return convertirADTO(
                comunaRepository.save(comuna));
    }

    public Comuna actualizarComuna(Integer id, Comuna comuna) {
        Optional<Comuna> comunaBuscada = comunaRepository.findById(id);
        if(comunaBuscada.isPresent()) {
            Comuna com = comunaBuscada.get();
            com.setNombreComuna(comuna.getNombreComuna());
            return comunaRepository.save(com);
        }

        return null;
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
        return dto;
    }

    private Comuna convertirAEntidad(ComunaDTO dto) {
        Comuna comuna = new Comuna();
        comuna.setId(dto.getId());
        comuna.setNombreComuna(dto.getNombreComuna());
        return comuna;
    }
}