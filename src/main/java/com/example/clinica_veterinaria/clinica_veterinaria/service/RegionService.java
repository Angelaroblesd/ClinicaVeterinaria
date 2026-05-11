package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.RegionDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Region;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.RegionRepository;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepository;

    public List<RegionDTO> obtenerTodos() {
        return regionRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public RegionDTO buscarPorId(Integer id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));

        return convertirADTO(region);
    }

    public RegionDTO guardar(RegionDTO dto) {
        Region region = convertirAEntidad(dto);
        return convertirADTO(
                regionRepository.save(region));
    }

    public Region actualizarRegion(Integer id, Region region) {
        Region reg = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        reg.setNombreRegion(region.getNombreRegion());
        return regionRepository.save(reg);
    }

    public String eliminar(Integer id) {
        if(regionRepository.existsById(id)) {
            regionRepository.deleteById(id);
            return "Región eliminada exitosamente";
        }
        return "Región no encontrada";
    }

    private RegionDTO convertirADTO(Region region) {
        RegionDTO dto = new RegionDTO();
        dto.setId(region.getId());
        dto.setNombreRegion(region.getNombreRegion());
        return dto;
    }

    private Region convertirAEntidad(RegionDTO dto) {
        Region region = new Region();
        region.setId(dto.getId());
        region.setNombreRegion(dto.getNombreRegion());
        return region;
    }
}