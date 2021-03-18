package com.financas.filtro;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LancamentoFiltro {
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataDe;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAte;

}
