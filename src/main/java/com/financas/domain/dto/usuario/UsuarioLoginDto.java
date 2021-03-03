package com.financas.domain.dto.usuario;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioLoginDto {

	@NotBlank(message = "usuário deve ser informado")
	private String username;

	@NotBlank(message = "senha deve ser informada")
	private String senha;

}
