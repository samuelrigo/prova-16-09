package com.example.prova_19_04_2024.entity;

import com.example.prova_19_04_2024.entity.Disciplina;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@Entity
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Curso {

    @Id
    private String nome;
    private Integer cargaHoraria;
    private Date dataInicio;
    private Disciplina nomeDisciplina;

}
