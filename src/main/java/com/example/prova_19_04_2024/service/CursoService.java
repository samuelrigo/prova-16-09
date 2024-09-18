package com.example.prova_19_04_2024.service;


import com.example.prova_19_04_2024.dtos.CursoDto;
import com.example.prova_19_04_2024.dtos.DisciplinaDto;
import com.example.prova_19_04_2024.entity.Curso;
import com.example.prova_19_04_2024.entity.Disciplina;
import com.example.prova_19_04_2024.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso createCurso(CursoDto data) {
        Curso curso = new Curso();
        curso.setNome(data.nome());
        curso.setCargaHoraria(data.cargaHoraria());
        curso.setDataInicio(data.dataInicio());

        if (data.disciplinas() != null) {
            List<Disciplina> disciplinas = data.disciplinas().stream()
                    .map(dto -> new Disciplina(null, dto.getNome()))
                    .toList();
            disciplinas.forEach(curso::addDisciplina);
        }

        this.save(curso);
        return curso;
    }

    public void save(Curso curso) {
        this.cursoRepository.save(curso);
    }

    public Curso updateCurso(Long id, CursoDto data) {
        return cursoRepository.findById(id)
                .map(existingCurso -> {
                    existingCurso.setNome(data.nome());
                    existingCurso.setCargaHoraria(data.cargaHoraria());
                    existingCurso.setDataInicio(data.dataInicio());

                    if (data.disciplinas() != null) {
                        List<Disciplina> disciplinas = data.disciplinas().stream()
                                .map(dto -> new Disciplina(null, dto.getNome()))
                                .toList();
                        disciplinas.forEach(existingCurso::addDisciplina);
                    }

                    return cursoRepository.save(existingCurso);
                }).orElseThrow(() -> new EntityNotFoundException("Curso not found"));
    }

    public List<Curso> findAll() {
        return cursoRepository.findAll();
    }

    public Curso findById(Long id) {
        return cursoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Curso not found"));
    }

    public void deleteCurso(Long id) {
        Curso curso = this.findById(id);
        cursoRepository.delete(curso);
    }

    public Curso removeDisciplina(Long cursoId, String nomeDisciplina) {
        return cursoRepository.findById(cursoId)
                .map(curso -> {
                    Disciplina disciplinaToRemove = curso.getDisciplinas().stream()
                            .filter(disciplina -> disciplina.getNome().equals(nomeDisciplina))
                            .findFirst()
                            .orElseThrow(() -> new EntityNotFoundException("Disciplina not found"));

                    curso.removeDisciplina(disciplinaToRemove);

                    return cursoRepository.save(curso);
                })
                .orElseThrow(() -> new EntityNotFoundException("Curso not found"));
    }

    public Curso addDisciplina(Long cursoId, DisciplinaDto disciplinaDto) {
        return cursoRepository.findById(cursoId)
                .map(curso -> {
                    Disciplina newDisciplina = new Disciplina(null, disciplinaDto.nome());
                    curso.addDisciplina(newDisciplina);
                    return cursoRepository.save(curso);
                })
                .orElseThrow(() -> new EntityNotFoundException("Curso not found"));
    }
}