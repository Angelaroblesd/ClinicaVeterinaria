package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Especie;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.EspecieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EspecieService {

    @Autowired
    private EspecieRepository especieRepository;

    public List<Especie> obtenerTodos() {
        return especieRepository.findAll();
    }

    public Especie guardar(Especie especie) {
        return especieRepository.save(especie);
    }

    public String eliminar(Integer id) {

        if(especieRepository.existsById(id)) {
            especieRepository.deleteById(id);
            return "Especie eliminada";
        }

        return "Especie no encontrada";
    }
}