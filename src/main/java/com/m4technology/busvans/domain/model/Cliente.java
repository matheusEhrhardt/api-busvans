package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    @Column(name = "DATA_NASCIMENTO")
    private LocalDate dataNascimento;
    private String email;
    private Long contato;
}
