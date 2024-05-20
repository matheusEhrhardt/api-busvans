package com.m4technology.busvans.domain.repository;


import com.m4technology.busvans.domain.model.Veiculo;
import com.m4technology.busvans.domain.model.VeiculoRota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRotaRepository extends JpaRepository<VeiculoRota, Long> {
}
