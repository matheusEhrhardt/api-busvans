package com.m4technology.busvans.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "localizacao_veiculo")
public class LocalizacaoVeiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "id_veiculo")
    @JsonIgnore
    private Veiculo veiculo;
    private Long latitude;
    private Long longitude;
    @CreationTimestamp
    private LocalDateTime atualizacao;
}
