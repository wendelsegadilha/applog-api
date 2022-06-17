package xyz.wendelsegadilha.applog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.domain.model.Entrega;
import xyz.wendelsegadilha.applog.domain.repository.EntregaRepository;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private BuscaEntregaService buscaEntregaService;
	private EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar (Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}

}
