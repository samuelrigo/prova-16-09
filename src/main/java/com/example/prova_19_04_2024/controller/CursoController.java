package com.example.prova_19_04_2024.controller;

import com.example.prova_19_04_2024.controller.dto.CursoDTO;
import com.example.prova_19_04_2024.entity.Curso;
import com.example.prova_19_04_2024.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;


    @GetMapping
    public List<CursoDTO> listar() {
        List<Curso> cursos = cursoRepository.findAll();
        return CursoDTO.converter(cursos);
    }


    @Transactional
    @PostMapping
    public void salvar(@RequestBody Curso curso) {
        cursoRepository.save(curso);
    }


    @Transactional
    @PutMapping
    public void atualizar(@RequestBody Curso curso) {
        cursoRepository.save(curso);
    }


    @Transactional
    @DeleteMapping
    public void deletar(@PathVariable Long isbn){
        cursoRepository.deleteById(isbn);
    }

}
