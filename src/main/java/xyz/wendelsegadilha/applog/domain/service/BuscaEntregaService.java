package xyz.wendelsegadilha.applog.domain.service;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.domain.exception.EntidadeNaoEncontradaException;
import xyz.wendelsegadilha.applog.domain.model.Entrega;
import xyz.wendelsegadilha.applog.domain.repository.EntregaRepository;

@Service
@AllArgsConstructor
public class BuscaEntregaService {
	
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}

}
