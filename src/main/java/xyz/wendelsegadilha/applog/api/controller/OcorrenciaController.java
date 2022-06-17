package xyz.wendelsegadilha.applog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.api.assembler.OcorrenciaAssembler;
import xyz.wendelsegadilha.applog.api.model.OcorrenciaModel;
import xyz.wendelsegadilha.applog.api.model.input.OcorrenciaInput;
import xyz.wendelsegadilha.applog.domain.model.Entrega;
import xyz.wendelsegadilha.applog.domain.model.Ocorrencia;
import xyz.wendelsegadilha.applog.domain.service.BuscaEntregaService;
import xyz.wendelsegadilha.applog.domain.service.RegistroOcorrenciaService;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencia")
public class OcorrenciaController {

	private RegistroOcorrenciaService registroOcorrenciaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	private BuscaEntregaService buscaEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {

		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId,
				ocorrenciaInput.getDescricao());
		
		OcorrenciaModel model = ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
		
		return model;

	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId){
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}

}
