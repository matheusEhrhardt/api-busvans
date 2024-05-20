package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.PrecoPassagem;
import com.m4technology.busvans.domain.repository.PrecoPassagemRepository;
import org.springframework.stereotype.Service;

@Service
public class PrecoPassagemService extends GenericService<PrecoPassagemRepository, PrecoPassagem> {

    public PrecoPassagemService() {
        super(new PrecoPassagem());
    }
}
