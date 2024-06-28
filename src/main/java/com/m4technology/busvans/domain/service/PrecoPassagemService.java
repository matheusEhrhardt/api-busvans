package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PrecoPassagemDTO;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.PrecoPassagem;
import com.m4technology.busvans.domain.repository.PrecoPassagemDetailRepository;
import com.m4technology.busvans.domain.repository.PrecoPassagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrecoPassagemService extends GenericService<PrecoPassagemRepository, PrecoPassagem> {

    @Autowired
    private PrecoPassagemDetailRepository detailRepository;

    public PrecoPassagemService() {
        super(new PrecoPassagem());
    }

    public List<PrecoPassagemDTO> consultaPrecoPassagem() {
        return detailRepository.consultaPrecoPassagem();
    }
}
