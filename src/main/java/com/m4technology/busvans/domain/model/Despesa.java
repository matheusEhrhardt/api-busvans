package com.m4technology.busvans.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.m4technology.busvans.domain.enums.TipoDespesaEnum;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
public class Despesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private TipoDespesaEnum tipo;
    @JsonIgnore
    @CreationTimestamp
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA")
    private Empresa empresa;
    @Transient
    private Long idEmpresa;
}
