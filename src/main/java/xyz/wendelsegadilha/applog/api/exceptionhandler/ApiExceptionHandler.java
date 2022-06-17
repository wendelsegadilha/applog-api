package xyz.wendelsegadilha.applog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.AllArgsConstructor;
import xyz.wendelsegadilha.applog.domain.exception.EntidadeNaoEncontradaException;
import xyz.wendelsegadilha.applog.domain.exception.NegocioException;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	/**
	 * Resolve o arquivo de mensagem messages.properties
	 */
	private MessageSource messageSource;
	
	/**
	 * Customização da exception de resposta para o cliente da API
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problema.Campo> campos = new ArrayList<>();
		
		/**
		 * Percorre a lista de erros atribuidas à requisição
		 */
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			/**
			 * Faz um cast explícito de ObjectError para FieldError 
			 * para ficar disponível o nome do campo o qual aconteceu um erro
			 */
			String nome = ((FieldError) error).getField();
			/**
			 * Pega as mensagens de validação do arquivo messages.properties
			 */
			String menssagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			campos.add(new Problema.Campo(nome, menssagem));
		}
		
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo("Um ou mais campos estão inválidos. Faça o preencimento correto e tente novamente.");
		problema.setCampos(campos);
		return handleExceptionInternal(ex, problema, headers, status, request);
	}
	
	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> handleEntidadeNaoEncontradaException(EntidadeNaoEncontradaException ex, WebRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo(ex.getMessage());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request); 
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Problema problema = new Problema();
		problema.setStatus(status.value());
		problema.setDataHora(LocalDateTime.now());
		problema.setTitulo(ex.getMessage());
		return handleExceptionInternal(ex, problema, new HttpHeaders(), status, request); 
	}
}
