package com.example.prova_19_04_2024.dtos;

import com.example.prova_19_04_2024.entity.Disciplina;

import java.time.LocalDate;
import java.util.List;

public record CursoDto(
        String nome,
        Integer cargaHoraria,
        LocalDate dataInicio,
        List<Disciplina> disciplinas
) {}