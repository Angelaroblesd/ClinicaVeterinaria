package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.MascotaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Mascota;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.MascotaRepository;

@Service
@Transactional
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    public List<MascotaDTO> obtenerTodos() {
        return mascotaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public MascotaDTO buscarPorId(Integer id) {
        Mascota mascota = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        return convertirADTO(mascota);
    }

    public MascotaDTO guardar(MascotaDTO dto) {
        Mascota mascota = convertirAEntidad(dto);
        Mascota nuevaMascota = mascotaRepository.save(mascota);
        return convertirADTO(nuevaMascota);
    }

    public Mascota actualizarMascota(Integer id, Mascota mascota) {
        Mascota masc = mascotaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mascota no encontrada"));

        masc.setNombre(mascota.getNombre());
        masc.setEdad(mascota.getEdad());

        return mascotaRepository.save(masc);
    }

    public String eliminar(Integer id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return "Mascota eliminada exitosamente";
        }

        return "Mascota no encontrada";
    }

    private MascotaDTO convertirADTO(Mascota mascota) {
        MascotaDTO dto = new MascotaDTO();
        dto.setId(mascota.getId());
        dto.setNombre(mascota.getNombre());
        dto.setEdad(mascota.getEdad());
        return dto;
    }

    private Mascota convertirAEntidad(MascotaDTO dto) {
        Mascota mascota = new Mascota();
        mascota.setId(dto.getId());
        mascota.setNombre(dto.getNombre());
        mascota.setEdad(dto.getEdad());
        return mascota;
    }
}