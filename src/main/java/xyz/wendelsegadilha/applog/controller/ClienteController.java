package xyz.wendelsegadilha.applog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.domain.model.Cliente;
import xyz.wendelsegadilha.applog.domain.repository.ClienteRepository;

@AllArgsConstructor
@RestController
public class ClienteController {
	
	private ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

}
