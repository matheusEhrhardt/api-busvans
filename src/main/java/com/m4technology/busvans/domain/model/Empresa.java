package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private String email;
    private String contato;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Veiculo> veiculos;
}
