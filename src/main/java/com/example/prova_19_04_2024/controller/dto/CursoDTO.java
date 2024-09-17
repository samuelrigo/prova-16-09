package com.example.prova_19_04_2024.controller.dto;

import com.example.prova_19_04_2024.entity.Curso;
import lombok.Getter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class CursoDTO {
    private String nome;
    private Integer cargaHoraria;
    private Date dataInicio;

    public CursoDTO(Curso curso){
        nome = curso.getNome();
        cargaHoraria = curso.getCargaHoraria();
        dataInicio = curso.getDataInicio();
    }

    public static List<CursoDTO> converter(List<Curso> cursos){
        return cursos.stream().map(CursoDTO::new).collect(Collectors.toList());
    }

}
