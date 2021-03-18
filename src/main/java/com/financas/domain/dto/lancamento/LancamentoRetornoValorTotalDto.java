package com.financas.domain.dto.lancamento;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoRetornoValorTotalDto {
	
	private List<LancamentoRetornoDto> lancamentos;
	
	private BigDecimal valorTotal;
	

}
