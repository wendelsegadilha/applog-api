package xyz.wendelsegadilha.applog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.wendelsegadilha.applog.domain.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Bob");
		cliente1.setEmail("bob99@gmail.com");
		cliente1.setTelefone("98 98674-9852");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Maria");
		cliente2.setEmail("mariasilva@gmail.com");
		cliente2.setTelefone("98 98745-9233");
				
		return Arrays.asList(cliente1, cliente2);
	}

}
