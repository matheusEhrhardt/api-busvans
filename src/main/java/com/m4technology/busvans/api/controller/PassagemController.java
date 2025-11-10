package com.m4technology.busvans.api.controller;

import com.m4technology.busvans.domain.generic.GenericController;
import com.m4technology.busvans.domain.model.Cliente;
import com.m4technology.busvans.domain.model.Passagem;
import com.m4technology.busvans.domain.service.ClienteService;
import com.m4technology.busvans.domain.service.PassagemService;
import com.m4technology.busvans.domain.dto.GerarPassagemDTO;
import com.m4technology.busvans.domain.model.Rota;
import com.m4technology.busvans.domain.model.VeiculoRota;
import com.m4technology.busvans.domain.model.Cliente;
import com.m4technology.busvans.domain.model.Passagem;
import com.m4technology.busvans.domain.service.RotaService;
import com.m4technology.busvans.domain.service.VeiculoRotaService;
import com.m4technology.busvans.domain.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Passagens")
@RestController
@RequestMapping("/passagens")
public class PassagemController extends GenericController<PassagemService, Passagem> {

	@Autowired
	private RotaService rotaService;

	@Autowired
	private VeiculoRotaService veiculoRotaService;

	@Autowired
	private ClienteService clienteService;

	@PostMapping("/gerar-por-trajeto")
	@ResponseStatus(HttpStatus.CREATED)
	public Passagem gerarPorTrajeto(@RequestBody @Valid GerarPassagemDTO dto){

		// busca rota pela cidade de partida/chegada
		Rota rota = rotaService.buscarPorPartidaChegada(dto.getIdCidadePartida(), dto.getIdCidadeChegada());

		// busca um veículo associado à rota (primeiro disponível)
		VeiculoRota veiculoRota = veiculoRotaService.buscarPorRotaId(rota.getId());

		Passagem passagem = new Passagem();
		passagem.setVeiculoRota(veiculoRota);
		passagem.setDataViagem(dto.getDataViagem());
		passagem.setQuantidade(dto.getQuantidade() != null ? dto.getQuantidade() : 1);

		if (dto.getIdCliente() != null){
			Cliente cliente = clienteService.buscarPorId(dto.getIdCliente());
			passagem.setCliente(cliente);
		}

		return service.salvar(passagem);
	}
}
