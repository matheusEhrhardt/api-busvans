package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Empresa;
import com.m4technology.busvans.domain.repository.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends GenericService<EmpresaRepository, Empresa> {

    public EmpresaService() {
        super(new Empresa());
    }
}
