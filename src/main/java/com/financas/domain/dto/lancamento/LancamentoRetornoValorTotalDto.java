package com.financas.domain.dto.lancamento;

import java.util.List;

import com.financas.domain.dto.ValorTotalDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoRetornoValorTotalDto {
	
	private List<LancamentoRetornoDto> lancamentos;
	
	private ValorTotalDto valorTotal;
	

}
