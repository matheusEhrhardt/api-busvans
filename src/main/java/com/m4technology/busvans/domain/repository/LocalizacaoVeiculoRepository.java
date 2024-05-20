package com.m4technology.busvans.domain.repository;


import com.m4technology.busvans.domain.model.LocalizacaoVeiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalizacaoVeiculoRepository extends JpaRepository<LocalizacaoVeiculo, Long> {
}
