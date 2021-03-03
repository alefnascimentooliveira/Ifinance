package com.financas.domain.dto.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioCadastroDto {

	@NotBlank(message = "username deve ser informado")
	@Size(min = 5, max = 100, message = "username precisa estar entre 5 e 100 caracteres")
	private String username;

	@NotBlank(message = "email deve ser informado")
	@Size(min = 5, max = 100, message = "email precisa estar entre 5 e 100 caracteres")
	private String email;

	@NotBlank(message = "senha deve ser informada")
	@Size(min = 5, max = 100, message = "senha precisa estar entre 5 e 100 caracteres")
	private String senha;

	@NotBlank(message = "nome deve ser informado")
	@Size(min = 5, max = 100, message = "nome precisa estar entre 5 e 100 caracteres")
	private String nome;

}
