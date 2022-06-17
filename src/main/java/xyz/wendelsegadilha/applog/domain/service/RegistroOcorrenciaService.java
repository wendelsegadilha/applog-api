package xyz.wendelsegadilha.applog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.domain.model.Entrega;
import xyz.wendelsegadilha.applog.domain.model.Ocorrencia;

@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {
	
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descicao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		return entrega.adicionarOcorrencia(descicao);
	}
	
}
