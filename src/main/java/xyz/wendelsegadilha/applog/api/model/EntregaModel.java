package xyz.wendelsegadilha.applog.api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import xyz.wendelsegadilha.applog.domain.model.StatusEntrega;

@Setter
@Getter
public class EntregaModel {
	
	private Long id;
	private String nomeCliente;
	private DestinatarioModel destinatario;
	private BigDecimal taxa;
	private StatusEntrega status;
	private LocalDateTime dataPedido;
	private LocalDateTime dataFinalizacao;

}
