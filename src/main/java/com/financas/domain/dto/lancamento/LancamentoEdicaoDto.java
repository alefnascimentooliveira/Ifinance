package com.financas.domain.dto.lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoEdicaoDto {

	private String tipo;

	private String descricao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;

	private BigDecimal valor;

	private Long categoria;

}
