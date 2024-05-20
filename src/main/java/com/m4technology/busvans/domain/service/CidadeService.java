package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Cidade;
import com.m4technology.busvans.domain.repository.CidadeRepository;
import org.springframework.stereotype.Service;

@Service
public class CidadeService extends GenericService<CidadeRepository, Cidade> {

    public CidadeService() {
        super(new Cidade());
    }
}
