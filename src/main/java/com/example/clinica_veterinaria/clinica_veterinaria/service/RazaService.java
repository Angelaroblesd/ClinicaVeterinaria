package com.example.clinica_veterinaria.clinica_veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.clinica_veterinaria.clinica_veterinaria.model.Raza;
import com.example.clinica_veterinaria.clinica_veterinaria.repository.RazaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class RazaService {

    @Autowired
    private RazaRepository razaRepository;

    public List<Raza> obtenerTodos() {
        return razaRepository.findAll();
    }

    public Raza guardar(Raza raza) {
        return razaRepository.save(raza);
    }

    public String eliminar(Integer id) {
        if(razaRepository.existsById(id)) {
            razaRepository.deleteById(id);
            return "Raza eliminada";
        }
        return "Raza no encontrada";
    }
}