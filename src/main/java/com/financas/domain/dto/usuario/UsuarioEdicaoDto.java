package com.financas.domain.dto.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioEdicaoDto {
	
	@NotBlank(message = "username precisa ser informado")
	private String username;

	@NotBlank(message = "email precisa ser informado")
	private String email;

	@NotBlank(message = "senha precisa ser informada")
	private String senha;

	@NotBlank(message = "nome precisa ser informado")
	private String nome;


}
