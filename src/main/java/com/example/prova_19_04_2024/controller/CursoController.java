package com.example.prova_19_04_2024.controller;

import com.example.prova_19_04_2024.entity.Curso;
import com.example.prova_19_04_2024.dtos.CursoDto;
import com.example.prova_19_04_2024.dtos.DisciplinaDto;
import com.example.prova_19_04_2024.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/curso")
public class CursoController {

    @Autowired
    CursoService cursoService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Curso> create(@RequestBody CursoDto cursoDto) {
        Curso newCurso = cursoService.createCurso(cursoDto);
        return ResponseEntity.ok().body(newCurso);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<Curso> update(@PathVariable Long id, @RequestBody CursoDto cursoDto) {
        Curso updateCurso = cursoService.updateCurso(id, cursoDto);
        return ResponseEntity.ok().body(updateCurso);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<Curso>> findAll() {
        List<Curso> cursos = cursoService.findAll();
        return ResponseEntity.ok().body(cursos);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<Curso> findById(@PathVariable Long id) {
        Curso curso = cursoService.findById(id);
        return ResponseEntity.ok().body(curso);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}/disciplina")
    public ResponseEntity<Curso> removeDisciplina(@PathVariable Long id, @RequestParam String nomeDisciplina) {
        Curso updatedCurso = cursoService.removeDisciplina(id, nomeDisciplina);
        return ResponseEntity.ok().body(updatedCurso);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{id}/disciplinas")
    public ResponseEntity<Curso> addDisciplina(@PathVariable Long id, @RequestBody DisciplinaDto disciplinaDto) {
        Curso curso = cursoService.addDisciplina(id, disciplinaDto);
        return ResponseEntity.ok().body(curso);
    }
}