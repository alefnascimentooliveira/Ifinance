package com.financas.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.financas.util.Causa;
import com.financas.util.Erro;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private static final String MENSAGEM_PADRAO = "Alguns dos campos estão inválidos";

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult bindingResult = ex.getBindingResult();

		List<FieldError> fieldErros = bindingResult.getFieldErrors();

		List<Causa> causas = new ArrayList<>();

		for (FieldError fieldError : fieldErros) {
			Causa causa = new Causa();
			causa.setCampo(fieldError.getField());
			causa.setCausa(fieldError.getDefaultMessage());
			causas.add(causa);
		}

		Erro erro = new Erro();
		erro.setCodigo(status.value());
		erro.setMensagem(MENSAGEM_PADRAO);
		erro.setStatus(status.getReasonPhrase());
		erro.setCausas(causas);

		return new ResponseEntity<Object>(erro, status);

	}

}
