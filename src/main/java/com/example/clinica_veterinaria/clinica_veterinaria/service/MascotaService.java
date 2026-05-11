package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.MascotaDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Dueno;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Especie;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Mascota;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Raza;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.DuenoRepository;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.EspecieRepository;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.MascotaRepository;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.RazaRepository;

@Service
@Transactional
public class MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private DuenoRepository duenoRepository;

    @Autowired
    private EspecieRepository especieRepository;

    @Autowired
    private RazaRepository razaRepository;

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
        masc.setSexo(mascota.getSexo());

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
        dto.setSexo(mascota.getSexo());

        dto.setDueno(mascota.getDueno().getNombre());
        dto.setEspecie(mascota.getEspecie().getNombreEspecie());
        dto.setRaza(mascota.getRaza().getNombreRaza());

        return dto;
    }

    private Mascota convertirAEntidad(MascotaDTO dto) {

        Mascota mascota = new Mascota();

        mascota.setId(dto.getId());
        mascota.setNombre(dto.getNombre());
        mascota.setEdad(dto.getEdad());
        mascota.setSexo(dto.getSexo());

        Dueno dueno = duenoRepository.findByNombre(dto.getDueno())
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));

        Especie especie = especieRepository.findByNombreEspecie(dto.getEspecie())
                .orElseThrow(() -> new RuntimeException("Especie no encontrada"));

        Raza raza = razaRepository.findByNombreRaza(dto.getRaza())
                .orElseThrow(() -> new RuntimeException("Raza no encontrada"));

        mascota.setDueno(dueno);
        mascota.setEspecie(especie);
        mascota.setRaza(raza);

        return mascota;
    }
}