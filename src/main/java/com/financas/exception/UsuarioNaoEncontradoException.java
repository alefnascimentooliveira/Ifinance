package com.financas.exception;

@SuppressWarnings("serial")
public class UsuarioNaoEncontradoException extends RuntimeException{

	public UsuarioNaoEncontradoException(String message) {
		super(message);
	}
}
