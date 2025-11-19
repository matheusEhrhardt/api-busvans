package com.m4technology.busvans.domain.repository;


import com.m4technology.busvans.domain.model.PrecoPassagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PrecoPassagemRepository extends JpaRepository<PrecoPassagem, Long> {
    Optional<PrecoPassagem> findFirstByLocalPartidaIdAndLocalChegadaId(Long localPartidaId, Long localChegadaId);
}
