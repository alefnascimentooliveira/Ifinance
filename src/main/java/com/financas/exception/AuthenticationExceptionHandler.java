package com.financas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.financas.util.Erro;

@ControllerAdvice
public class AuthenticationExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<Object> handleAuthenticationExceptionHandler(BadCredentialsException e) {
		Erro erro = new Erro();
		erro.setCodigo(HttpStatus.FORBIDDEN.value());
		erro.setMensagem(e.getMessage());
		erro.setStatus(HttpStatus.FORBIDDEN.getReasonPhrase());
		return new ResponseEntity<Object>(erro, HttpStatus.FORBIDDEN);
	}

}
