package xyz.wendelsegadilha.applog.domain.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.domain.model.Cliente;
import xyz.wendelsegadilha.applog.domain.model.Entrega;
import xyz.wendelsegadilha.applog.domain.model.StatusEntrega;
import xyz.wendelsegadilha.applog.domain.repository.EntregaRepository;

@AllArgsConstructor
@Service
public class SolicitacaoEntregaService {

	private EntregaRepository entregaRepository;
	private CatalogoClienteService catalogoClienteService;

	@Transactional
	public Entrega solicitar(Entrega entrega) {
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(LocalDateTime.now());
		entrega = entregaRepository.save(entrega);
		
		Cliente cliente = catalogoClienteService.buscar(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		
		return entrega;
	}

}
