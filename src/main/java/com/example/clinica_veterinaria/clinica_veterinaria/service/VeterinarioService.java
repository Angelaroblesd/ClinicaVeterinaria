package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.clinica_veterinaria.clinica_veterinaria.DTO.VeterinarioDTO;
import com.example.clinica_veterinaria.clinica_veterinaria.model.Veterinario;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.VeterinarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VeterinarioService {
    @Autowired
    private VeterinarioRepository veterinarioRepository;

    private VeterinarioDTO convertirADTO(Veterinario veterinario) {
       VeterinarioDTO dto = new VeterinarioDTO();
       dto.setId(veterinario.getId());
       dto.setNombre(veterinario.getNombreVeterinario());
       dto.setTelefono(veterinario.getTelefono());
       dto.setEspecialidad(veterinario.getEspecialidad());

       if (veterinario.getConsultas() != null) {//da el total de consultas si esque hay 
        dto.setTotalConsultas(veterinario.getConsultas().size()) ;
       }
       else {
          dto.setTotalConsultas(0);//si no hay consultas muestra 0
            }
              return dto;
    }

    public List<VeterinarioDTO> obtenerTodos() {
       return veterinarioRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .toList();
    }

    public VeterinarioDTO buscarPorId(Integer id) {
       Veterinario veterinario = veterinarioRepository.findById(id)
          .orElseThrow(() -> new RuntimeException("veterinario no encontrado"));
       return convertirADTO(veterinario);
    }

    
    public String eliminar(Integer id) {
     try {
           Veterinario veterinario = veterinarioRepository.findById(id)
                   .orElseThrow(() -> new RuntimeException("¡Imposible eliminar! el veterinario con ID " + id + " no existe."));
           veterinarioRepository.delete(veterinario);
           return "la clinica '" + veterinario.getNombreVeterinario() + "' ha sido retirada exitosamente.";
       } catch (RuntimeException e) {
           return e.getMessage();
       }
    }

    public Veterinario guardarVeterinario(Veterinario veterinario) {
       return veterinarioRepository.save(veterinario);
    }

    public Veterinario actualizarVeterinario(Integer id,Veterinario vet){
       Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(() -> new RuntimeException("la clinica no existe"));
       if(vet.getNombreVeterinario() != null){
           veterinario.setNombreVeterinario(vet.getNombreVeterinario());
       }
       if(vet.getTelefono() != null){
           veterinario.setTelefono(vet.getTelefono());
       }
       if(vet.getEspecialidad() != null){
           veterinario.setEspecialidad(vet.getEspecialidad());
       }
       if(vet.getClinica() != null){
          veterinario.setClinica(vet.getClinica());
       }
       return veterinarioRepository.save(veterinario);
    }
    
    //metodo para buscar por especialidad
    public List<VeterinarioDTO> buscarPorEspecialidad(String especialidad) {
            List<Veterinario> veterinarios = veterinarioRepository.findByEspecialidad(especialidad);
            List<VeterinarioDTO> listaDTO = new ArrayList<>();
         for (Veterinario veterinario : veterinarios) {
                listaDTO.add(convertirADTO(veterinario));
            }
        return listaDTO;
    }

}
