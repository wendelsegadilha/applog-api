package xyz.wendelsegadilha.applog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DestinatarioIdInput {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	@NotBlank
	private String complemento;
	
	@NotBlank
	private String bairro;

}
