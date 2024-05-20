package com.m4technology.busvans.domain.repository;


import com.m4technology.busvans.domain.model.Rota;
import org.springframework.stereotype.Repository;

@Repository
public interface RotaRepository extends org.springframework.data.jpa.repository.JpaRepository<Rota, Long> {
}
