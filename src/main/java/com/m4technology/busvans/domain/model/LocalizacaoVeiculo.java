package com.m4technology.busvans.domain.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "LOCALIZACAO_VEICULO")
public class LocalizacaoVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "ID_VEICULO")
    private Veiculo veiculo;
    private Long latitude;
    private Long longitude;
    @CreationTimestamp
    private LocalDateTime atualizacao;
}
