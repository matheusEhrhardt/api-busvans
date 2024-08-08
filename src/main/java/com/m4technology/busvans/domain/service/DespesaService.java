package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.exception.EntidadeNaoEncontradaException;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Despesa;
import com.m4technology.busvans.domain.model.Empresa;
import com.m4technology.busvans.domain.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class DespesaService extends GenericService<DespesaRepository, Despesa> {

    @Autowired
    private EmpresaService empresaService;

    public DespesaService() {
        super(new Despesa());
    }

    @Override
    public Despesa salvar(Despesa model) {

        Empresa empresa = empresaService.buscarPorId(model.getIdEmpresa());

        if (empresa == null){
            throw new EntidadeNaoEncontradaException("Não foi possivel encontrar empresa com id: " + model.getIdEmpresa());
        }

        model.setEmpresa(empresa);

        return super.salvar(model);
    }
}
