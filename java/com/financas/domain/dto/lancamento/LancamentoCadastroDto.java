package com.financas.domain.dto.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoCadastroDto {

	@NotBlank(message = "tipo deve ser informado")
	private String tipo;

	@NotBlank(message = "descrição deve ser informada")
	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "data deve ser informada")
	private LocalDate data;

	@NotNull(message = "valor deve ser informado")
	private BigDecimal valor;

	@NotNull(message = "categoria deve ser informada")
	// codigo da categoria
	private Long categoria;

	@NotNull(message = "usuário deve ser informado")
	private String usernameUsuario;

}
