package com.example.clinica_veterinaria.clinica_veterinaria.service;
import java.util.List;
import java.util.stream.Collectors;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_veterinaria.clinica_veterinaria.DTO.DuenoDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Dueno;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.DuenoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class DuenoService {

    @Autowired
    private DuenoRepository duenoRepository;

    public List<DuenoDTO> obtenerTodos() {
        return duenoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    public DuenoDTO buscarPorId(Integer id) {
        Optional<Dueno> duenoBuscado = duenoRepository.findById(id);
        if(duenoBuscado.isPresent()) {
            Dueno dueno = duenoBuscado.get();
            return convertirADTO(dueno);
    }

    return null;
    }

    public DuenoDTO guardar(DuenoDTO dto) {
    Optional<Dueno> existente = duenoRepository.findByRut(dto.getRut());
    if(existente.isPresent()){
        throw new RuntimeException("El rut ya existe");
    }
    Dueno dueno = convertirAEntidad(dto);
    return convertirADTO(duenoRepository.save(dueno));
}

    public Dueno actualizarDueno(Integer id, Dueno dueno) {
        Dueno d = duenoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));

        d.setNombre(dueno.getNombre());
        d.setTelefono(dueno.getTelefono());
        return duenoRepository.save(d);
    }

    public String eliminar(Integer id) {
        if (duenoRepository.existsById(id)) {
            duenoRepository.deleteById(id);
            return "Dueño eliminado exitosamente";
        }
        return "Dueño no encontrado";
    }

    private DuenoDTO convertirADTO(Dueno dueno) {
        DuenoDTO dto = new DuenoDTO();
        dto.setId(dueno.getId());
        dto.setRut(dueno.getRut());
        dto.setNombre(dueno.getNombre());
        dto.setTelefono(dueno.getTelefono());
        dto.setDireccion(dueno.getDireccion());
        dto.setMail(dueno.getMail());
        return dto;
    }

    private Dueno convertirAEntidad(DuenoDTO dto) {
        Dueno dueno = new Dueno();
        dueno.setId(dto.getId());
        dueno.setRut(dto.getRut());
        dueno.setNombre(dto.getNombre());
        dueno.setTelefono(dto.getTelefono());
        dueno.setDireccion(dto.getDireccion());
        dueno.setMail(dto.getMail());
        return dueno;
    }

    public DuenoDTO buscarPorNombre(String nombre) {
    Dueno dueno = duenoRepository.findByNombre(nombre)
            .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
    return convertirADTO(dueno);
    }

    public String aplicarDescuento(Integer id) {
        Dueno dueno = duenoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dueño no encontrado"));
        int cantidadMascotas = dueno.getMascotas().size();
        if (cantidadMascotas >= 5) {
            return "El dueño " + dueno.getNombre() +
                " tiene descuento del 10%";
        }
        return "El dueño " + dueno.getNombre() +
            " no tiene descuento";
    }
}