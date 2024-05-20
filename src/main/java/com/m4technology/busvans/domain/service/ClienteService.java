package com.m4technology.busvans.domain.service;

import com.m4technology.busvans.domain.dto.PagamentoPassagemDTO;
import com.m4technology.busvans.domain.generic.GenericService;
import com.m4technology.busvans.domain.model.Cliente;
import com.m4technology.busvans.domain.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class ClienteService extends GenericService<ClienteRepository, Cliente> {

    public ClienteService() {
        super(new Cliente());
    }

    public Cliente salvar(PagamentoPassagemDTO passagemDTO){
        Cliente cliente = convertPassagemToCliente(passagemDTO);
        return salvar(cliente);
    }

    public Cliente convertPassagemToCliente(PagamentoPassagemDTO passagemDTO){
        Cliente cliente = new Cliente();
        cliente.setNome(passagemDTO.getNomeCliente());
        cliente.setEmail(passagemDTO.getEmail());
        cliente.setCpf(passagemDTO.getCpfCliente());
        cliente.setDataNascimento(passagemDTO.getDataNascimento());

        return cliente;
    }
}
